package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborateurRepository extends JpaRepository <Collaborateur, Long>{
}
