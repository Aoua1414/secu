package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository <user, Long> {
    Optional<user> findBypseudo(String pseudo, String nom);
    Boolean existBypseudo(String pseudo);

}
