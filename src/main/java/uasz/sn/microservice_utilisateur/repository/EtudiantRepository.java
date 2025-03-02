package uasz.sn.microservice_utilisateur.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.microservice_utilisateur.modele.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
