package uasz.sn.microservice_utilisateur.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.microservice_utilisateur.modele.Utilisateur;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
            Utilisateur findByUsername(String username);


}