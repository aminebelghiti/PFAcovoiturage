package ma.emsi.covoiturage.repository;


import ma.emsi.covoiturage.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface TrajetRepository extends JpaRepository<Trajet,Long> {
}
