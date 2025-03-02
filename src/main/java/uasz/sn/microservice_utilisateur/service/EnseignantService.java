package uasz.sn.microservice_utilisateur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.microservice_utilisateur.modele.Enseignant;
import uasz.sn.microservice_utilisateur.repository.EnseignantRepository;

import java.util.List;

@Service
@Transactional
public class EnseignantService {
    @Autowired
    private EnseignantRepository enseignantRepository;

    public Enseignant ajouter(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public List<Enseignant> lister() {
        return enseignantRepository.findAll();
    }

    public Enseignant rechercher(Long id) {
        return enseignantRepository.findById(id).get();
    }

    public Enseignant modifier(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public void supprimer(Long id) {
        enseignantRepository.deleteById(id);
    }

    public void activer(Long id){
        Enseignant enseignant=enseignantRepository.findById(id).get();
        if (enseignant.isActive()==true){enseignant.setActive(false);}
        else {enseignant.setActive(true);}
        enseignantRepository.save(enseignant);
    }
    public void archiver (Long id){
        Enseignant enseignant = enseignantRepository.findById(id).get();
        if (enseignant.isArchive() == true) {
            enseignant.setArchive(false);
        } else {enseignant.setArchive(true);}
        enseignantRepository.save(enseignant);

    }
    public List<Enseignant> findAll() {
        return enseignantRepository.findAll();
    }

    public Enseignant findById(Long id) {
        return enseignantRepository.findById(id).get();
    }

    public Enseignant trouverParEmail(String username) {
        return enseignantRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Enseignant non trouvé avec l'email : " + username));
    }
    public Enseignant getEnseignantById(Long enseignantId) {
        return enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec l'ID : " + enseignantId));
    }
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }
}
