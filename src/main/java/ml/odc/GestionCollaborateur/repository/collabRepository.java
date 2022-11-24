package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface collabRepository extends JpaRepository <collaborateur, Long> {

}
