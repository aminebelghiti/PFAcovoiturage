package ma.emsi.covoiturage.service.ImplementationServices;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.repository.ConducteurRepository;
import ma.emsi.covoiturage.service.ConducteurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ConducteurImpl implements ConducteurService {
    private final ConducteurRepository repository;
    @Override
    public Conducteur saveConducteur(Conducteur conducteur) {
        return repository.save(conducteur);
    }

    @Override
    public Optional<Conducteur> getConducteur(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<Conducteur> getAllConducteur() {
        return repository.findAll();
    }

    @Override
    public Conducteur updateConducteur(Conducteur conducteur) {
        return repository.save(conducteur);
    }

    @Override
    public void deleteConducteur(Long id) {
            repository.deleteById(id);
    }
}
