package ml.odc.GestionCollaborateur.service;

import lombok.ToString;
import ml.odc.GestionCollaborateur.model.Profil;
import ml.odc.GestionCollaborateur.model.User;
import ml.odc.GestionCollaborateur.repository.ProfilRepository;
import ml.odc.GestionCollaborateur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@ToString
public class CompteServiceImpl implements CompteService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository utilisateurRepository;
    @Autowired
    ProfilRepository profilRepository;


    @Override
    public User addNewUser(User utilisateur) {
        String pw = utilisateur.getPassword();
        utilisateur.setPassword(passwordEncoder.encode(pw));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Profil addNewProfile(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public void addProfileToUser(String username, String profileName) {

        User utilisateur = utilisateurRepository.findByUserName(username);
        Profil profil = profilRepository.findByProfileName(profileName);

        System.out.println("Voici l'utilisateur voulu : "+utilisateur+" son username est : "+username);
        System.out.println("Voici le profil voulu : "+profil+" sa clé de recherche est : "+username);

        utilisateur.getProfils().add(profil);

    }

    @Override
    public User loadUserByUsername(String username) {
        return utilisateurRepository.findByUserName(username);
    }

    @Override
    public List<User> listUsers() {
        return utilisateurRepository.findAll();
    }

}
