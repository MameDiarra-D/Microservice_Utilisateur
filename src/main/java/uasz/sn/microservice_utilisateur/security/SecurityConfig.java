package uasz.sn.microservice_utilisateur.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import uasz.sn.microservice_utilisateur.jwt.JwtFilter;
import uasz.sn.microservice_utilisateur.jwt.JwtUtils;
import uasz.sn.microservice_utilisateur.service.UtilisateurDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UtilisateurDetailsService utilisateurDetailsService;
    private final JwtUtils jwtUtils;
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()  // Routes d'authentification accessibles à tous
                        .requestMatchers("/h2/**").permitAll()    // Permet l'accès à H2-console si nécessaire
                        .requestMatchers("/api/**").permitAll()  // Ajoutez cette ligne pour autoriser les routes de l'API
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Utilisation de JWT, pas de session
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Ajout du filtre JWT
                .build();
    }
}
