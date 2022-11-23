package ml.odc.GestionCollaborateur.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class collaborateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String nom;
    String pseudo;

}
