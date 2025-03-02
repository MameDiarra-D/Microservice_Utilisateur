package uasz.sn.microservice_utilisateur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.microservice_utilisateur.modele.Permanant;
import uasz.sn.microservice_utilisateur.repository.PermanantRepository;

import java.util.List;

@Service
@Transactional
public class PermanantService {
    @Autowired
    private PermanantRepository permanantRepository;

    public Permanant ajouter(Permanant permanant) {
        return permanantRepository.save(permanant);
    }

    public List<Permanant> lister() {
        return permanantRepository.findAll();
    }

    public Permanant rechercher(Long id) {
        return permanantRepository.findById(id).get();
    }

    public Permanant modifier(Permanant permanant) {
        return permanantRepository.save(permanant);
    }

    public void supprimer(Long id) {
        permanantRepository.deleteById(id);
    }
}

