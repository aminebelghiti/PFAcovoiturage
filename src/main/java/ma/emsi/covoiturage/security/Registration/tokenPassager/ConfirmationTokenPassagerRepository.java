package ma.emsi.covoiturage.security.Registration.tokenPassager;

import ma.emsi.covoiturage.security.Registration.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenPassagerRepository extends JpaRepository<ConfirmationTokenPassager,Long> {
    Optional<ConfirmationTokenPassager> findByToken (String token);
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationTokenPassager c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
