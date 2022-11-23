package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface profilRepository extends JpaRepository <profil, Long> {
    Optional <profil> findBynom(String nom);
}
