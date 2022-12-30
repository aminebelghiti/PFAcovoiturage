package ma.emsi.covoiturage.repository;

import ma.emsi.covoiturage.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole (String role);
}
