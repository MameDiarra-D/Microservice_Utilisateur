package uasz.sn.microservice_utilisateur.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uasz.sn.microservice_utilisateur.modele.Utilisateur;
import uasz.sn.microservice_utilisateur.repository.UtilisateurRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        return new org.springframework.security.core.userdetails.User(
                utilisateur.getUsername(),
                utilisateur.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(utilisateur.getRole()))
        );

    }
}
