package ml.odc.GestionCollaborateur.service;

import ml.odc.GestionCollaborateur.model.Role;
import ml.odc.GestionCollaborateur.model.User;
import ml.odc.GestionCollaborateur.repository.RoleRepository;
import ml.odc.GestionCollaborateur.repository.userRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional

public class compteImpl implements compte {
    private userRepository UserRepository;
    private RoleRepository roleRepository;

    public compteImpl(userRepository userRepository, RoleRepository roleRepository) {
        UserRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public compteImpl() {
    }

    @Override
    public User ajoutNewUser(User user) {
        return UserRepository.save(user);
    }

    @Override
    public Role ajoutNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void ajoutRoleToUser(String username, String roleName) {
     User user=UserRepository.findByuserName(username);
     Role role=roleRepository.findByroleName(roleName);
     user.getRoles().add(role);
    }

    @Override
    public User afficherUserParUsername(String username) {

        return UserRepository.findByuserName(username);
    }

    @Override
    public List<User> listUsers() {

        return UserRepository.findAll();
    }
}
