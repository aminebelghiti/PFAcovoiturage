package ma.emsi.covoiturage.security.Registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String nom;
    private final String prenom;
    private final String username;
    private final String email;
    private final String password;
    private final int telephone;
    private final String CIN;
    private final String sexe;
    private final String n_permis;
    private final Integer nbr_Trajets;
}
