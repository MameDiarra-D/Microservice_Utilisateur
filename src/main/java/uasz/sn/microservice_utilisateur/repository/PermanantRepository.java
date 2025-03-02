package uasz.sn.microservice_utilisateur.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.microservice_utilisateur.modele.Permanant;
@Repository
public interface PermanantRepository extends JpaRepository<Permanant, Long> {
}
