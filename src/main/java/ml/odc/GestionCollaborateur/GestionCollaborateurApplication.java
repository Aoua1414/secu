package ml.odc.GestionCollaborateur;

import ml.odc.GestionCollaborateur.model.Profil;
import ml.odc.GestionCollaborateur.model.User;
import ml.odc.GestionCollaborateur.repository.ProfilRepository;
import ml.odc.GestionCollaborateur.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class GestionCollaborateurApplication {

	@Autowired
	ProfilRepository profilRepository;
	public static void main(String[] args) {
		SpringApplication.run(GestionCollaborateurApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(CompteService accountService) {
		return args -> {

			//Ajout des rôles
			if (profilRepository.findAll().size() == 0) {
				accountService.addNewProfile(new Profil(null,"USER"));
				accountService.addNewProfile(new Profil(null,"ADMIN"));
			}

			//Ajout des utilisateur initiales
			if (accountService.listUsers().size() == 0) {
				accountService.addNewUser(new User(null,"geek","0000",new ArrayList<>()));
				//Attribution du rôle de geek
				accountService.addProfileToUser("geek","ADMIN");

				accountService.addNewUser(new User(null,"oussou017","1234",new ArrayList<>()));
				//Attribution du rôle de oussou
				accountService.addProfileToUser("oussou017","USER");

				accountService.addNewUser(new User(null,"aoua1414","aoua1414",new ArrayList<>()));
				//Attribution du rôle de aoua1414
				accountService.addProfileToUser("aoua1414","USER");

			}

		};
	}

}
