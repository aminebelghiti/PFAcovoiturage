package ma.emsi.covoiturage.service.ImplementationServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.covoiturage.model.Passager;
import ma.emsi.covoiturage.repository.PassagerRepository;
import ma.emsi.covoiturage.service.PassagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PassagerImpl implements PassagerService {
    private final PassagerRepository passagerRepository;
    @Override
    public Passager savePassager(Passager passager) {
        return passagerRepository.save(passager);
    }

    @Override
    public Passager getPassager(String username) {
        return passagerRepository.findPassagerByUsername(username);
    }

    @Override
    public List<Passager> getAllPassager() {
        return passagerRepository.findAll();
    }

    @Override
    public Passager updatePassager(Passager passager) {
        return passagerRepository.save(passager);
    }

    @Override
    public void deletePassagee(Long id) {
        passagerRepository.deleteById(id);
    }
}
