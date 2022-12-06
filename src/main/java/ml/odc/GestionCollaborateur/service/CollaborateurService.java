package ml.odc.GestionCollaborateur.service;

import ml.odc.GestionCollaborateur.model.Collaborateur;
import ml.odc.GestionCollaborateur.model.Profil;
import ml.odc.GestionCollaborateur.model.User;

import java.util.List;

public interface CollaborateurService {

    //Service pour la gestion des collaborateurs
    Collaborateur addNewCollabo(Collaborateur collaborateur);
    List<Collaborateur> listCollabos();
    Collaborateur updateCollabo(long id, Collaborateur collaborateur);
    Collaborateur deleteCollabo(long id);

}
