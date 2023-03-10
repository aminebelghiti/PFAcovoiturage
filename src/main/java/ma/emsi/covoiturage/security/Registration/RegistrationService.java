package ma.emsi.covoiturage.security.Registration;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.mail.EmailSender;
import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.model.Passager;
import ma.emsi.covoiturage.model.Role;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationToken;
import ma.emsi.covoiturage.security.Registration.token.ConfirmationTokenService;
import ma.emsi.covoiturage.security.Registration.tokenPassager.ConfirmationTokenPassager;
import ma.emsi.covoiturage.security.Registration.tokenPassager.ConfirmationTokenServicePassager;
import ma.emsi.covoiturage.service.RegistrationAppService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidation emailValidation;
    private final RegistrationAppService registrationAppService;

    private final EmailSender sender;
    private final ConfirmationTokenService service;
    private final ConfirmationTokenServicePassager servicePassager;
    public String register(RegistrationRequest request) {
        boolean isValidEmail =emailValidation.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email non valide");
        }
        String token = registrationAppService.signUpConducteur(
                new Conducteur(
                        request.getNom(),
                        request.getPrenom(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getTelephone(),
                        request.getCIN(),
                        request.getSexe(),
                        request.getN_permis(),
                        request.getNbr_Trajets(),
                        Role.CONDUCTEUR
                )
        );
        String link = "http://localhost:8080/conducteur/register/confirm?token=" + token;
        sender.send(
                request.getEmail(),
                buildEmail(request.getUsername(), link));
        return token;
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
        registrationAppService.enableConducteur(
                confirmationToken.getConducteur().getEmail());
        return "confirmed";
    }

    public String registerPassager(RegistrationPassagerRequest request) {
        boolean isValidEmail =emailValidation.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email non valide");
        }
        String token = registrationAppService.signUpPassager(
                new Passager(
                        request.getNom(),
                        request.getPrenom(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getTelephone(),
                        request.getCIN(),
                        request.getSexe()
                )
        );
        String link = "http://localhost:8080/passager/confirm?token=" + token;
        sender.send(
                request.getEmail(),
                buildEmail(request.getUsername(), link));
        return token;
    }
    @Transactional
    public String confirmTokenP(String token) {
        ConfirmationTokenPassager confirmationTokenPassager = servicePassager
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationTokenPassager.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationTokenPassager.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        servicePassager.setConfirmedAt(token);
        registrationAppService.enablePassager(
                confirmationTokenPassager.getPassager().getEmail());
        return "confirmed";
    }



    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
