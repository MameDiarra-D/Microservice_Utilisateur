package uasz.sn.microservice_utilisateur.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_utilisateur.modele.Permanant;
import uasz.sn.microservice_utilisateur.service.EnseignantService;
import uasz.sn.microservice_utilisateur.service.PermanantService;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PermenantRestController {
    @Autowired
    private PermanantService permanantService;
    @Autowired
    private EnseignantService enseignantService;

    @GetMapping(path = "/permanants")
    public List lister_Permanant() {
        return permanantService.lister();
    }

    @PostMapping(path = "/permanant")
    public Permanant ajouter_Permanant(@RequestBody Permanant permanant) {
        return permanantService.ajouter(permanant);
    }

    @GetMapping(path = "/permanant/{id}")
    public Permanant rechercher_Permanant( @PathVariable Long id) {
        return permanantService.rechercher(id);
    }

    @PutMapping(path ="/permanant" )
    public Permanant modifier_Permanantt(@RequestBody Permanant permanant) {
        return permanantService.modifier(permanant);
    }

    @DeleteMapping(path = "/permanant/{id}")
    public String supprimer_Permanant( @PathVariable Long id) {
       permanantService.supprimer(id);
        return "Permenent supprimer avec succes";
    }

    @PostMapping("/activerPermanent")
    public ResponseEntity<String> activerPermanent(@RequestParam Long id) {
        enseignantService.activer(id);
        return ResponseEntity.ok("Enseignant activé avec succès");
    }

    @PostMapping("/archiverPermanent")
    public ResponseEntity<String> archiverPermanent(@RequestParam Long id) {
        enseignantService.archiver(id);
        return ResponseEntity.ok("Enseignant archivé avec succès");
    }
}


