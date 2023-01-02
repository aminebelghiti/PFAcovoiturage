package ma.emsi.covoiturage.repository;

import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.model.Passager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PassagerRepository extends JpaRepository<Passager,Long> {
    Passager findPassagerByUsername(String username);
    Optional<Conducteur> findByEmail (String email);

    @Transactional
    @Modifying
    @Query("UPDATE Conducteur a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
