package ma.emsi.covoiturage.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
public class Passager implements Serializable, UserDetails {
    @Id
    @GeneratedValue
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
    private String role;
    private Boolean locked =false;
    private Boolean enabled= false;

    @ManyToMany(mappedBy = "passagers")
    private Collection<Trajet> trajets;
    public Passager(String nom, String prenom, String username, String email, String password, int telephone, String CIN, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.CIN = CIN;
        this.sexe = sexe;
    }
    public Passager()
    {
        this.role="Passager";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("passager");
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
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
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername()
    {
        return email;
    }
}

