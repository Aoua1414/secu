package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    @Query(value = "SELECT user.id, user.username, user.password FROM user WHERE user.username = :username",nativeQuery = true)
    User findByUserName(String username);

}
