package ml.odc.GestionCollaborateur.repository;

import ml.odc.GestionCollaborateur.model.Role;
import ml.odc.GestionCollaborateur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query (value = "SELECT role.id, role.rolename FROM role WHERE role.rolename = :rolename",nativeQuery = true)
    Role findByrolename(String rolename);
}
