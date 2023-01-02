package ma.emsi.covoiturage.service;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.model.Passager;
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
import java.util.UUID;

@Service
@AllArgsConstructor
public class PassagerAppService implements UserDetailsService {
    private final PassagerRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final ConfirmationTokenService service;
    private final ConfirmationTokenServicePassager servicePassager;

    private final static String PASSAGER_NOT_FOUND="Passager avec %s est non trouvable";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return    repository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format(PASSAGER_NOT_FOUND,email)));
    }
    public String signUpPassager(Passager passager){
        boolean ConducteurExist=  repository.findByEmail(passager.getEmail())
                .isPresent();
        if(ConducteurExist)
        {
            throw new IllegalStateException("Email deja existante");
        }
        String EncodedPassword =
                passwordEncoder.encode(passager.getPassword());
        passager.setPassword(EncodedPassword);

        repository.save(passager);
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
    public int enableAppUser(String email) {
        return repository.enableAppUser(email);
    }
}
