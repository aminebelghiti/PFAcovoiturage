package ma.emsi.covoiturage.repository;

import ma.emsi.covoiturage.model.Passager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassagerRepository extends JpaRepository<Passager,Long> {
    Passager findPassagerByUsername(String username);
}
