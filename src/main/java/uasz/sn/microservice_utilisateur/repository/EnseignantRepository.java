package uasz.sn.microservice_utilisateur.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.microservice_utilisateur.modele.Enseignant;

import java.util.Optional;
@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

    Optional<Enseignant> findByUsername(String username);

}
