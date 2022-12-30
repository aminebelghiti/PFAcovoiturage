package ma.emsi.covoiturage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conducteur extends User implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String n_permis;
    private Integer nbr_Trajets;
}
