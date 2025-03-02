package uasz.sn.microservice_utilisateur.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.microservice_utilisateur.exception.ResourceAlreadyExistException;
import uasz.sn.microservice_utilisateur.exception.ResourceNotFoundException;
import uasz.sn.microservice_utilisateur.jwt.JwtUtils;
import uasz.sn.microservice_utilisateur.modele.Utilisateur;
import uasz.sn.microservice_utilisateur.repository.UtilisateurRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public Utilisateur ajouter(Utilisateur utilisateur) {
        Utilisateur existe = utilisateurRepository.findByUsername(utilisateur.getUsername());
        if (existe != null) {
            throw new ResourceAlreadyExistException(
                    "Le username " + utilisateur.getUsername() + " existe déjà"
            );
        }
        try {
            return utilisateurRepository.save(utilisateur);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erreur lors de l Ajout");
        }
    }

    public Utilisateur rechercher(String username) {
        try {
            return utilisateurRepository.findByUsername(username);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Utilisateur " + username + " n existe pas");
        }
    }
}
