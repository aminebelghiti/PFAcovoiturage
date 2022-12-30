package ma.emsi.covoiturage.service;

import ma.emsi.covoiturage.model.Role;
import ma.emsi.covoiturage.model.User;

import java.util.List;

public interface UserService {
    User saveUser (User user);
    Role saveRole (Role role);
    void addRoleToUser(String Username,String RoleName);
    User getUser(String username);
    List<User> getUsers();
}
