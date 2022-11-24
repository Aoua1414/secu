package ml.odc.GestionCollaborateur.controller;

import ml.odc.GestionCollaborateur.model.collaborateur;
import ml.odc.GestionCollaborateur.service.collabService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaborateur")
public class collabController {
    collabService CollabService;
    @GetMapping("/afficher_liste")
    public List<collaborateur>afficher(){
        return CollabService.afficher();
    }
    @PostMapping ("/ajouter")
    public String ajouter(@RequestBody collaborateur Collaborateur) {
    CollabService.ajouter(Collaborateur);
    return ("collab ajoute");
}
    @PostMapping("/modifier/{id}")
    public String modifierCollabo(@RequestBody collaborateur Collaborateur, @PathVariable long id) {
        if (CollabService.modifier (Collaborateur,id) != null) {
            return ("Modification reussie");
        } else {
            return ("Cet collaborateur n'existe pas");
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public  String supprimerCollabo (@PathVariable long id) {
        if (CollabService.supprimer(id) != null) {
            return ("Collaborateur supprimer avec succès !");
        } else {
            return ("Ce collaborateur n'existe plus !");
        }
    }

    }
