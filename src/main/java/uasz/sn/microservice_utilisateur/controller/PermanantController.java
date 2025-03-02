package uasz.sn.microservice_utilisateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.microservice_utilisateur.modele.Permanant;
import uasz.sn.microservice_utilisateur.modele.Utilisateur;
import uasz.sn.microservice_utilisateur.service.EnseignantService;
import uasz.sn.microservice_utilisateur.service.PermanantService;
import uasz.sn.microservice_utilisateur.service.UtilisateurService;

import javax.management.relation.Role;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PermanantController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private PermanantService permanantService;

    @RequestMapping(value = "/Permanant/Accueil", method = RequestMethod.GET)
    public String accueilPermanant(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_Permanent";
    }

    @RequestMapping(value = "/ChefDepartement/Accueil", method = RequestMethod.GET)
    public String accueilChefDepartement(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_ChefDepartement";
    }
    @RequestMapping(value = "/ChefDepartement/ajouterPermanent", method = RequestMethod.POST)
    public String ajouter_Permanent(Permanant permanent) {

        String password = passwordEncoder.encode("Passer123");
        permanent.setPassword(password);
        permanent.setDateCreation(new Date());
        permanent.setActive(true);


        enseignantService.ajouter(permanent);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value = "/ChefDepartement/modifierPermanent", method = RequestMethod.POST)
    public String modifier_Permanent(Permanant permanant) {
        Permanant per_modif = permanantService.rechercher(permanant.getId());
        per_modif.setMatricule(permanant.getMatricule());
        per_modif.setNom(permanant.getNom());
        per_modif.setPrenom(permanant.getPrenom());
        per_modif.setSpecialite(permanant.getSpecialite());
        per_modif.setGrade(permanant.getGrade());

        enseignantService.modifier(per_modif);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value = "/ChefDepartement/activerPermanent", method = RequestMethod.POST)
    public String activer_Permanent(Long id) {
        enseignantService.activer(id);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value = "/ChefDepartement/archiverPermanent", method = RequestMethod.POST)
    public String archiver_Permanent(Long id) {
        enseignantService.archiver(id);
        return "redirect:/ChefDepartement/Enseignant";
    }
}

