package ma.emsi.covoiturage.repository;

import ma.emsi.covoiturage.model.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConducteurRepository extends JpaRepository<Conducteur,Long> {
    Optional<Conducteur> findByEmail (String email);
    @Transactional
    @Modifying
    @Query("UPDATE Conducteur a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableConducteur(String email);

}
