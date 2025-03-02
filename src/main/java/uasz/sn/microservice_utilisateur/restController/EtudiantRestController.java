package uasz.sn.microservice_utilisateur.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_utilisateur.modele.Etudiant;
import uasz.sn.microservice_utilisateur.service.EtudiantService;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EtudiantRestController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping(path = "/etudiants")
    public List lister_Etudiant() {
        return etudiantService.lister();
    }

    @PostMapping(path = "/etudiant")
    public Etudiant ajouter_Etudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.ajouter(etudiant);
    }

    @GetMapping(path = "/etudiant/{id}")
    public Etudiant rechercher_Etudiant( @PathVariable Long id) {
        return etudiantService.rechercher(id);
    }

    @PutMapping(path ="/etudiant" )
    public Etudiant modifier_Etudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.modifier(etudiant);
    }

    @DeleteMapping(path = "/etudiant/{id}")
    public String supprimer_Etudiant( @PathVariable Long id) {
        etudiantService.supprimer(id);
        return "Etudiant supprimer avec succes";
    }


}


