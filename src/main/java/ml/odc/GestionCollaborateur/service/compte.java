package ml.odc.GestionCollaborateur.service;

import ml.odc.GestionCollaborateur.model.Role;
import ml.odc.GestionCollaborateur.model.User;

import java.util.List;

public interface compte {
    User ajoutNewUser(User user);
    Role ajoutNewRole(Role role);
    void  ajoutRoleToUser(String username, String roleName);
    User afficherUserParUsername(String username);
    List<User> listUsers();
}
