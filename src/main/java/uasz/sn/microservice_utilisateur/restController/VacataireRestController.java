package uasz.sn.microservice_utilisateur.restController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_utilisateur.modele.Vacataire;
import uasz.sn.microservice_utilisateur.service.EnseignantService;
import uasz.sn.microservice_utilisateur.service.VacataireService;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class VacataireRestController {
  @Autowired
    private VacataireService vacataireService;

    @Autowired
    private EnseignantService enseignantService;
    @GetMapping(path = "/vacataires")
    public List lister_Vacataire() {
        return vacataireService.lister();
    }

    @PostMapping(path = "/vacataire")
    public Vacataire ajouter_Vacataire(@RequestBody Vacataire vacataire) {
        return vacataireService.ajouter(vacataire);
    }

    @GetMapping(path = "/vacataire/{id}")
    public Vacataire rechercher_Vacataire(@PathVariable Long id) {
        return vacataireService.rechercher(id);
    }

    @PutMapping(path = "/vacataire")
        public Vacataire modifier_Vacataire(@RequestBody Vacataire vacataire) {
        return vacataireService.modifier(vacataire);
    }

    @DeleteMapping(path = "/vacataire/{id}")
    public String supprimer_Vacataire(@PathVariable Long id) {
        vacataireService.supprimer(id);
        return "Vacataire supprimer avec succes";
    }

    @PostMapping("/activerVacataire")
    public ResponseEntity<String> activerVacataire(@RequestParam Long id) {
        enseignantService.activer(id);
        return ResponseEntity.ok("Vacataire activé avec succès");
    }

    @PostMapping("/archiverVacataire")
    public ResponseEntity<String> archiverVacataire(@RequestParam Long id) {
        enseignantService.archiver(id);
        return ResponseEntity.ok("Vacataire archivé avec succès");
    }
}
