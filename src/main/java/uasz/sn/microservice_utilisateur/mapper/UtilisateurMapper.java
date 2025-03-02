package uasz.sn.microservice_utilisateur.mapper;

import org.mapstruct.Mapper;
import uasz.sn.microservice_utilisateur.dto.LoginDTO;
import uasz.sn.microservice_utilisateur.dto.UtilisateurDTO;
import uasz.sn.microservice_utilisateur.modele.Utilisateur;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
//    LoginDTO UtilisateurToDTO(Utilisateur utilisateur);
//
//    Utilisateur dtoToUtilisateur(UtilisateurDTO utilisateurDTO);
//
//    LoginDTO UtilisateurToLogin(Utilisateur utilisateur);
//
//    Utilisateur loginToUtilisateur(LoginDTO loginDTO);

    // Méthode existante (retourne LoginDTO)
    LoginDTO UtilisateurToDTO(Utilisateur utilisateur);

    // Ajoutez cette méthode pour mapper vers UtilisateurDTO
    UtilisateurDTO utilisateurToDTO(Utilisateur utilisateur);

    Utilisateur dtoToUtilisateur(UtilisateurDTO utilisateurDTO);
    LoginDTO UtilisateurToLogin(Utilisateur utilisateur);
    Utilisateur loginToUtilisateur(LoginDTO loginDTO);
}


