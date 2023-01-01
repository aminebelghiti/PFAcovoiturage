package ma.emsi.covoiturage.security.Registration;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.model.Role;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationToken;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationTokenService;
import ma.emsi.covoiturage.service.ConducteurAppService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidation emailValidation;
    private final ConducteurAppService conducteurAppService;

    private final ConfirmationTokenService service;
    public String register(RegistrationRequest request) {
        boolean isValidEmail =emailValidation.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email non valide");
        }

        return conducteurAppService.signUpConducteur(
                new Conducteur(
                        request.getNom(),
                        request.getPrenom(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getTelephone(),
                        request.getCIN(),
                        request.getSexe(),
                        Role.CONDUCTEUR
                )
        );
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = service
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        service.setConfirmedAt(token);
        conducteurAppService.enableAppUser(
                confirmationToken.getConducteur().getEmail());
        return "confirmed";
    }
}
