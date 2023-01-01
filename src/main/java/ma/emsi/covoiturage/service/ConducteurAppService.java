package ma.emsi.covoiturage.service;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.repository.ConducteurRepository;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationToken;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConducteurAppService implements UserDetailsService {
    private final static String Conducteur_NOT_FOUND="Conducteur avec %s est non trouvable";
    private final ConducteurRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final ConfirmationTokenService service;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format(Conducteur_NOT_FOUND,email)));
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
    public int enableAppUser(String email) {
        return repository.enableAppUser(email);
    }
}
