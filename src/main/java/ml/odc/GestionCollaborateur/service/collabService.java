package ml.odc.GestionCollaborateur.service;

import ml.odc.GestionCollaborateur.model.collaborateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface collabService {
        List<collaborateur> afficher();

        Object ajouter(collaborateur Collaborateur);

        Object modifier(collaborateur Collaborateur,long id);

        Object supprimer(long id);
    }

