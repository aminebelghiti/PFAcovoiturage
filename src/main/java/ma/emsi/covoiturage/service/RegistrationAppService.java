package ma.emsi.covoiturage.service;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.model.Passager;
import ma.emsi.covoiturage.repository.ConducteurRepository;
import ma.emsi.covoiturage.repository.PassagerRepository;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationToken;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationTokenService;
import ma.emsi.covoiturage.security.Registration.tokenPassager.ConfirmationTokenPassager;
import ma.emsi.covoiturage.security.Registration.tokenPassager.ConfirmationTokenServicePassager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationAppService implements UserDetailsService {
    private final static String Conducteur_NOT_FOUND="Conducteur avec %s est non trouvable";
    private final static String PASSAGER_NOT_FOUND="Passager avec %s est non trouvable";

    private final ConfirmationTokenServicePassager servicePassager;
    private final ConducteurRepository repository;
    private final PassagerRepository passagerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService service;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passager> passager = passagerRepository.findByEmail(email);
        if(passager.isPresent())
        {
            return passagerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(PASSAGER_NOT_FOUND, email)));
        }
        else  {
            return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(Conducteur_NOT_FOUND, email)));
        }
    }
     public String signUpConducteur(Conducteur conducteur){
      boolean ConducteurExist=  repository.findByEmail(conducteur.getEmail())
                .isPresent();
      if(ConducteurExist)
      {
          throw new IllegalStateException("Email deja existante");
      }
     String EncodedPassword =
             passwordEncoder.encode(conducteur.getPassword());
        conducteur.setPassword(EncodedPassword);

        repository.save(conducteur);
         String tokenUUID = UUID.randomUUID().toString();
         ConfirmationToken token = new ConfirmationToken(
            tokenUUID,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    conducteur
         );
         service.saveConfirmationToken(token);

        return tokenUUID;
     }
    public int enableConducteur(String email) {
        return repository.enableConducteur(email);
    }
    public String signUpPassager(Passager passager){
        boolean PassagerExist=  passagerRepository.findByEmail(passager.getEmail())
                .isPresent();
        if(PassagerExist)
        {
            throw new IllegalStateException("Email deja existante");
        }
        String EncodedPassword =
                passwordEncoder.encode(passager.getPassword());
        passager.setPassword(EncodedPassword);

        passagerRepository.save(passager);
        String tokenUUID = UUID.randomUUID().toString();
        ConfirmationTokenPassager token = new ConfirmationTokenPassager(
                tokenUUID,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                passager
        );
        servicePassager.saveConfirmationToken(token);

        return tokenUUID;
    }
    public int enablePassager(String email) {
        return passagerRepository.enableAppUser(email);
    }

}
