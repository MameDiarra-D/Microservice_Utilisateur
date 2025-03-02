package uasz.sn.microservice_utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.microservice_utilisateur.modele.Vacataire;

@Repository
public interface VacataireRepository extends JpaRepository<Vacataire, Long> {
}
