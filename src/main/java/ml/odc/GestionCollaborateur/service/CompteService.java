package ml.odc.GestionCollaborateur.service;

import ml.odc.GestionCollaborateur.model.Profil;
import ml.odc.GestionCollaborateur.model.User;

import java.util.List;

public interface CompteService {
    //Service pour la gestion des utilisateurs
    User addNewUser(User utilisateur);
    Profil addNewProfile(Profil profil);
    void addProfileToUser(String username, String profileName);
    User loadUserByUsername(String username);
    List<User> listUsers();
}