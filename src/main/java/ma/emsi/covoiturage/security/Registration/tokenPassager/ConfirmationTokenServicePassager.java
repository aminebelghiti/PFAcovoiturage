package ma.emsi.covoiturage.security.Registration.tokenPassager;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationToken;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConfirmationTokenServicePassager {
    private final ConfirmationTokenPassagerRepository repository;
    public void saveConfirmationToken(ConfirmationTokenPassager token)
    {
        repository.save(token);
    }
    public Optional<ConfirmationTokenPassager> getToken(String token) {
        return repository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return repository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
