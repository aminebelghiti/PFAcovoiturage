package ma.emsi.covoiturage.service.ImplementationServices;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.model.Trajet;
import ma.emsi.covoiturage.repository.TrajetRepository;
import ma.emsi.covoiturage.service.TrajetService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TrajetImpl implements TrajetService {

    private final TrajetRepository repository;
    @Override
    public Trajet saveTrajet(Trajet trajet) {
        return repository.save(trajet);
    }

    @Override
    public Optional<Trajet> getTrajet(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Trajet> getAllTrajets() {
        return repository.findAll();
    }

    @Override
    public Trajet updateTrajet(Trajet trajet) {
        return repository.save(trajet);
    }

    @Override
    public void deleteTrajet(Long id) {
        repository.deleteById(id);
    }

}
