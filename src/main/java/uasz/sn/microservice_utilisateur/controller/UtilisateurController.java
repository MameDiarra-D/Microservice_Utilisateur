package uasz.sn.microservice_utilisateur.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_utilisateur.dto.LoginDTO;
import uasz.sn.microservice_utilisateur.dto.UtilisateurDTO;
import uasz.sn.microservice_utilisateur.jwt.JwtUtils;
import uasz.sn.microservice_utilisateur.mapper.UtilisateurMapper;
import uasz.sn.microservice_utilisateur.modele.Utilisateur;
import uasz.sn.microservice_utilisateur.service.UtilisateurService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j

public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final UtilisateurMapper utilisateurMapper;

    @PostMapping(path = "/inscrire")
    public ResponseEntity<?> ajouter(@RequestBody UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurMapper.dtoToUtilisateur(utilisateurDTO);
        String password = passwordEncoder.encode("Passer123");
        utilisateur.setPassword(password);
        utilisateurService.ajouter(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurDTO);
    }
    @PostMapping(path = "/connecter")
    public ResponseEntity<?> authentifier(@RequestBody LoginDTO loginDTO) {
        Utilisateur utilisateur= utilisateurMapper.loginToUtilisateur(loginDTO);
        try {
            // Vérification des identifiants
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword())
            );

            if (authentication.isAuthenticated()) {
                System.out.println(authentication);
                Utilisateur user=utilisateurService.rechercher(utilisateur.getUsername());
                String role = user.getRole();
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(utilisateur.getUsername()));
                authData.put("type", "Bearer");
                authData.put("role", role);
                // AJOUT : on renvoie aussi nom et prenom
                authData.put("nom", user.getNom());
                authData.put("prenom", user.getPrenom());

                return ResponseEntity.ok(authData);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect !");
        } catch (AuthenticationException e) {
            log.error("Erreur d'authentification : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect !");
        }
    }
    @PostMapping(path = "/deconnecter")
    public ResponseEntity<?> deconnecter(HttpServletRequest request) {
        // Côté serveur, il n'y a pas de session à invalider avec JWT, mais vous pouvez ajouter des logs ou d'autres actions si nécessaire
        log.info("Utilisateur déconnecté.");
        return ResponseEntity.ok("Déconnexion réussie");
    }
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
//        // Récupérer le username depuis le contexte de sécurité
//        String username = authentication.getName();
//        Utilisateur user = utilisateurService.rechercher(username);
//
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable");
//        }
//
//        Map<String, Object> userData = new HashMap<>();
//        userData.put("username", user.getUsername());
//        userData.put("nom", user.getNom());
//        userData.put("prenom", user.getPrenom());
//        userData.put("role", user.getRole());
//        // Ajoutez d’autres champs si nécessaire
//
//        return ResponseEntity.ok(userData);
//    }
//    @PutMapping("/update")
//    public ResponseEntity<?> updateUser(Authentication authentication, @RequestBody UtilisateurDTO utilisateurDTO) {
//        String username = authentication.getName();
//        Utilisateur user = utilisateurService.rechercher(username);
//
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable");
//        }
//
//        // Mettre à jour les champs autorisés
//        user.setNom(utilisateurDTO.getNom());
//        user.setPrenom(utilisateurDTO.getPrenom());
//        // etc. (selon ce que vous voulez permettre)
//
//        utilisateurService.ajouter(user); // ou un update dédié
//
//        return ResponseEntity.ok("Profil mis à jour avec succès");
//    }
@GetMapping("/profil")
public ResponseEntity<?> getProfil(Authentication authentication) {
    String username = authentication.getName();
    Utilisateur user = utilisateurService.rechercher(username);

    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
    }

    UtilisateurDTO userDto = new UtilisateurDTO();
    userDto.setUsername(user.getUsername());
    userDto.setNom(user.getNom());
    userDto.setPrenom(user.getPrenom());
    // Si vous gérez l'email, décommentez :
    // userDto.setEmail(user.getEmail());

    return ResponseEntity.ok(userDto);
}

    /**
     * PUT /auth/profil
     * Met à jour les informations du profil de l'utilisateur connecté.
     */
    @PutMapping("/profil")
    public ResponseEntity<?> updateProfil(Authentication authentication, @RequestBody UtilisateurDTO utilisateurDTO) {
        String username = authentication.getName();
        Utilisateur user = utilisateurService.rechercher(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }

        user.setNom(utilisateurDTO.getNom());
        user.setPrenom(utilisateurDTO.getPrenom());
        // Si vous gérez l'email, décommentez la ligne suivante :
        // user.setEmail(utilisateurDTO.getEmail());

        // Enregistrez les modifications (selon votre service, utilisez update ou ajouter)
        utilisateurService.ajouter(user);

        return ResponseEntity.ok("Profil mis à jour avec succès");
    }

}
