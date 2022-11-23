package ml.odc.GestionCollaborateur.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String Nom;
    public String pseudo;
    public String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<profil> Profil;
}


