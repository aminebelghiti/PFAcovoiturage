package ma.emsi.covoiturage.service;

import ma.emsi.covoiturage.model.Conducteur;

import java.util.List;
import java.util.Optional;

public interface ConducteurService {
    Conducteur saveConducteur (Conducteur conducteur);
    Optional<Conducteur> getConducteur(String email);
    List<Conducteur> getAllConducteur();
    Conducteur updateConducteur (Conducteur conducteur);
    void deleteConducteur(Long id);
}
