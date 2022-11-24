package ml.odc.GestionCollaborateur.serviceImplem;

import ml.odc.GestionCollaborateur.model.collaborateur;
import ml.odc.GestionCollaborateur.repository.collabRepository;
import ml.odc.GestionCollaborateur.service.collabService;

import java.util.List;

public class collabImplem implements collabService {
    collabRepository collabRepository;

    @Override
    public List<collaborateur> afficher() {
        return  collabRepository.findAll();
    }

    @Override
    public Object ajouter(collaborateur Collaborateur) {
        collabRepository.save(Collaborateur );
        return ("collaborateur ajoute");
    }

    @Override
    public Object modifier(collaborateur Collaborateur, long id) {
            collaborateur collaborateurcourant = collabRepository.findById(id).orElse(null);
            if (collaborateurcourant != null) {
                return collabRepository.save(Collaborateur);
            } else return null;
        }

    @Override
    public Object supprimer(long id) {
        collaborateur collaborateurcourant = collabRepository.findById(id).orElse(null);
        if (collaborateurcourant != null) {
            collabRepository.deleteById(id);
            return collaborateurcourant;
        } else return("Cet collaborateur m'existe pas");
    }
    }