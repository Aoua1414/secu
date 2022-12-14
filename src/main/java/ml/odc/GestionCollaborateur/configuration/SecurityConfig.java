package ml.odc.GestionCollaborateur.configuration;

import ml.odc.GestionCollaborateur.Filter.JwtAuthentification;
import ml.odc.GestionCollaborateur.Filter.JwtAuthorization;
import ml.odc.GestionCollaborateur.model.User;
import ml.odc.GestionCollaborateur.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    CompteService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Désactiver la vérification csrf pour utiliser les sessions
        http.csrf().disable();

        //Utilisation de l'authentification stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();

        //Activer le formulaire d'authentification
        http.formLogin();

        //Ajouter OAuth2
        //http.oauth2Login();

        //Authoriser le lien du refresh token
        http.authorizeRequests().antMatchers("/refreshToken/**").permitAll();

        //Ajout des filtres
        http.addFilter(new JwtAuthentification(authenticationManagerBean()));
        //Pour permettre l'authentification avant toute requêtteos
        http.addFilterBefore(new JwtAuthorization(), UsernamePasswordAuthenticationFilter.class);

        //Demander l'authentification à chaque requette
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(username -> {

            User utilisateur = accountService.loadUserByUsername(username);

            //Conversion de la liste de rôle en granted authorities
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            utilisateur.getProfils().forEach(profil -> authorities.add(new SimpleGrantedAuthority(profil.getRole())));

            return new org.springframework.security.core.userdetails.User(utilisateur.getUsername(), utilisateur.getPassword(),authorities);
        });

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}


