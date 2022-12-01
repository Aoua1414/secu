package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.Role;
import ml.odc.GestionCollaborateur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository <User, Long>{
    @Query(value = "SELECT user.id, user.password, user.username FROM user WHERE user.username = :username",nativeQuery = true)
    User findByusername(String username);
}
