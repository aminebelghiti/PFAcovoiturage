package ma.emsi.covoiturage.service;

import ma.emsi.covoiturage.model.Passager;

import java.util.List;

public interface PassagerService {
    Passager savePassager (Passager passager);
    Passager getPassager(String username);
    List<Passager> getAllPassager();
    Passager updatePassager (Passager passager);
    void deletePassagee(Long id);
}
