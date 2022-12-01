package ml.odc.GestionCollaborateur.service;

import ml.odc.GestionCollaborateur.model.Role;
import ml.odc.GestionCollaborateur.model.User;
import ml.odc.GestionCollaborateur.repository.RoleRepository;
import ml.odc.GestionCollaborateur.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional

public class compteImpl implements compte {
    @Autowired
    private userRepository UserRepository;
    @Autowired
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
     User user=UserRepository.findByusername(username);
     Role role=roleRepository.findByrolename(roleName);
     user.getRoles().add(role);
    }

    @Override
    public User afficherUserParUsername(String username) {

        return UserRepository.findByusername(username);
    }

    @Override
    public List<User> listUsers() {

        return UserRepository.findAll();
    }
}
