package ma.emsi.covoiturage.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.repository.ConducteurRepository;
import ma.emsi.covoiturage.service.ConducteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ConducteurImpl implements ConducteurService {


    @Autowired
    private ConducteurRepository repository;
    @Override
    public List<Conducteur> getAllConducteur() {
        return repository.findAll();
    }

    @Override
    public Conducteur saveConducteur(Conducteur conducteur) {
        return repository.save(conducteur);
    }

    @Override
    public Conducteur getConducteurByUsername(String username) {
        return repository.findConducteurByUsername(username);
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
