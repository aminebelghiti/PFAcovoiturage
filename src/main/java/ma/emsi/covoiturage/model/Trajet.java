package ma.emsi.covoiturage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Trajet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String ville_Depart;
    private String ville_Arrivee;
    private LocalTime heure_Depart;
    private LocalTime heure_Arrivee;
    private LocalDate date_Trajet;
    private int capacite;
    private int nb_Places;
    private double prix;
    private double duree;
    @ManyToOne
    private Voiture voiture_id;
    @ManyToMany
    @JoinTable(
            name = "Reservation",
            joinColumns = @JoinColumn(name = "passager_id"),
            inverseJoinColumns = @JoinColumn(name = "trajet_id"))
    Collection<Passager> passagers;
    @OneToOne
    private Conducteur conducteur;


}
