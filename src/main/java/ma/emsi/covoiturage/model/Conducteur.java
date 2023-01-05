package ma.emsi.covoiturage.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Conducteur implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String password;
    private int telephone;
    @Column(unique = true)
    private String CIN;
    private String sexe;
    private String rating;
    private String n_permis;
    private int nbr_Trajets;
    private Boolean locked =false;
    private Boolean enabled= false;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    private Trajet trajet;
    public Conducteur(String nom, String prenom, String username, String email, String password, int telephone, String cin, String sexe, String n_permis, int nbr_trajets, Role conducteur) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.CIN = cin;
        this.sexe = sexe;
        this.n_permis = n_permis;
        this.nbr_Trajets = nbr_trajets;
        this.role = conducteur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername()
    {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
