package ma.emsi.covoiturage.repository;

import ma.emsi.covoiturage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
