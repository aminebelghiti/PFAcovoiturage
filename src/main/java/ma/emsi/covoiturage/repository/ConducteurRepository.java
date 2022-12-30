package ma.emsi.covoiturage.repository;

import ma.emsi.covoiturage.model.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConducteurRepository extends JpaRepository<Conducteur,Long> {
    Conducteur findConducteurByUsername (String username);
}
