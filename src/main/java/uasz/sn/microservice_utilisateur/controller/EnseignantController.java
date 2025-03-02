package uasz.sn.microservice_utilisateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.microservice_utilisateur.modele.Permanant;
import uasz.sn.microservice_utilisateur.modele.Utilisateur;
import uasz.sn.microservice_utilisateur.modele.Vacataire;
import uasz.sn.microservice_utilisateur.service.PermanantService;
import uasz.sn.microservice_utilisateur.service.UtilisateurService;
import uasz.sn.microservice_utilisateur.service.VacataireService;

import java.security.Principal;
import java.util.List;

@Controller
public class EnseignantController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PermanantService permanantService;

    @Autowired
    private VacataireService vacataireService;

    @RequestMapping(value = "/ChefDepartement/Enseignant", method = RequestMethod.GET)
    public String chefDepartement_Enseignant(Model model, Principal principal) {

        List<Permanant> permanents = permanantService.lister();
        model.addAttribute("permanents", permanents);

        List<Vacataire> vacataires = vacataireService.lister();
        model.addAttribute("vacataires", vacataires);


        return "chefDepartement_Enseignant";
    }
}

