package ma.emsi.covoiturage.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.covoiturage.model.Role;
import ma.emsi.covoiturage.model.User;
import ma.emsi.covoiturage.repository.RoleRepository;
import ma.emsi.covoiturage.repository.UserRepository;
import ma.emsi.covoiturage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving User to DataBase",user.getPrenom(),user.getPrenom());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role to DataBase",role.getRole());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String Username, String RoleName) {
        log.info("Adding Role to User");
        User user = userRepository.findByUsername(Username);
        Role role = roleRepository.findByRole(RoleName);
        user.getRoles().add(role);
    }
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
