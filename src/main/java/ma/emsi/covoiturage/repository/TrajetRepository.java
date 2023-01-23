package ma.emsi.covoiturage.repository;


import ma.emsi.covoiturage.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TrajetRepository extends JpaRepository<Trajet,Long> {

    @Query("FROM Trajet t WHERE t.ville_Depart = :villeDepart AND t.ville_Arrivee = :villeArrivee AND t.date_Trajet = :dateTrajet")
    List<Trajet> searchTrajetDateDeppartArrive(@RequestParam("vd") String villeDepart, @RequestParam("va") String villeArrivee, @RequestParam("d") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTrajet);
}
