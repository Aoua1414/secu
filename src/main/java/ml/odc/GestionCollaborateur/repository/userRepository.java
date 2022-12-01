package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.Role;
import ml.odc.GestionCollaborateur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository <User, Long>{
    User findByuserName(String username);
}
