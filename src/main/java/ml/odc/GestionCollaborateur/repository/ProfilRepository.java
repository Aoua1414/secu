package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfilRepository extends JpaRepository <Profil, Long> {

    @Query(value = "SELECT profil.id, profil.role FROM profil WHERE profil.role = :profil",nativeQuery = true)
    Profil findByProfileName(String profil);

}
