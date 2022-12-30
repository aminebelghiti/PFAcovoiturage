package ma.emsi.covoiturage.service;

import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.model.Role;
import ma.emsi.covoiturage.model.User;

import java.util.List;

public interface ConducteurService {
    List<Conducteur> getAllConducteur();

    Conducteur saveConducteur (Conducteur conducteur);

    Conducteur getConducteurByUsername (String username);

    Conducteur updateConducteur (Conducteur conducteur);

    void deleteConducteur(Long id);
}
