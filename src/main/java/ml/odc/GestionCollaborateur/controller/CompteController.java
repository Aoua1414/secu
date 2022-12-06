package ml.odc.GestionCollaborateur.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import ml.odc.GestionCollaborateur.model.Profil;
import ml.odc.GestionCollaborateur.model.RoleToUser;
import ml.odc.GestionCollaborateur.model.User;
import ml.odc.GestionCollaborateur.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CompteController {


    private CompteService accountService;

    //Pour retrouver tous les utilisateurs
    @GetMapping("/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<User> utilisateurs() {
        return accountService.listUsers();
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users")
    User saveUser(@RequestBody User utilisateur) {
        return accountService.addNewUser(utilisateur);
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/roles")
    Profil saveProfile(@RequestBody Profil profil) {
        return accountService.addNewProfile(profil);
    }

    @PostMapping("/addprofiletouser")
    @PostAuthorize("hasAuthority('ADMIN')")
    void addProfileToUser(@RequestBody RoleToUser roleUserModel) {
        accountService.addProfileToUser(roleUserModel.getUsername(), roleUserModel.getProfilename());
    }

    //Méthode pour rafraichir le token
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String authToken = request.getHeader("Authoriation");
        if (authToken != null && authToken.startsWith("Bearer ")) {
            try {
                String jwt = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("__inc0nnu__");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();

                //Chercher l'utilisateur dans la base de données
                User utilisateur = accountService.loadUserByUsername(username);

                //AccesToken
                String jwtAccessToken = JWT.create()
                        .withSubject(utilisateur.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("profils",utilisateur.getProfils().stream().map(grantedAuthority -> grantedAuthority.getRole()).collect(Collectors.toList()))
                        .  sign(algorithm);

                //Mettre la réponse dans un body / Envoyer le token
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Veiller fournir un refresh token");
        }
    }

}
