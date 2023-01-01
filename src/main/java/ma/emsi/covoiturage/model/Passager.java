package ma.emsi.covoiturage.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Passager implements Serializable {
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

    public Passager(Long ID, String nom, String prenom, String username, String email, String password, int telephone, String CIN, String sexe, String rating, String role) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.CIN = CIN;
        this.sexe = sexe;
        this.rating = rating;
        this.role = "PASSAGER";
    }
    public Passager()
    {
        this.role="Passager";
    }
}

