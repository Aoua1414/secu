package ml.odc.GestionCollaborateur;

import ml.odc.GestionCollaborateur.model.Role;
import ml.odc.GestionCollaborateur.model.User;
import ml.odc.GestionCollaborateur.service.compte;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class GestionCollaborateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCollaborateurApplication.class, args);
	}
@Bean
	CommandLineRunner start(compte compte) {
	return args -> {

		// ajout des roles
		compte.ajoutNewRole(new Role(null, "USER"));
		compte.ajoutNewRole(new Role(null, "ADMIN"));

        //ajout de nouveaux utilisateurs
		compte.ajoutNewUser(new User(null, "Aoua", "1234", new ArrayList<>()));
		compte.ajoutNewUser(new User(null, "Ali", "5678", new ArrayList<>()));

        //attribution des roles et utilisateurs
		compte.ajoutRoleToUser("Aoua", "USER");
		compte.ajoutRoleToUser("Ali","ADMIN");
	};
}

}
