package ma.emsi.covoiturage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String marque;
    @Column(unique = true)
    private String matricule;
    private String modele;
    private int nb_places;
    private String carte_grise;
    private int capacite;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "trajet_id", nullable = false)
    private Collection<Trajet> trajets;
}
