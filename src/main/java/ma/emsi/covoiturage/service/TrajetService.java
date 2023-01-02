package ma.emsi.covoiturage.service;

import ma.emsi.covoiturage.model.Trajet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TrajetService {
    Trajet saveTrajet (Trajet trajet);
    Optional<Trajet> getTrajet(Long id);
    List<Trajet> getAllTrajets();
    Trajet updateTrajet (Trajet trajet);
    void deleteTrajet(Long id);
}
