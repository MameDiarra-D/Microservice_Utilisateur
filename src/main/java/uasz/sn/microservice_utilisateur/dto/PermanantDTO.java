package uasz.sn.microservice_utilisateur.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class PermanantDTO {
        private Long id;
        private String username;
        private String nom;
        private String prenom;
        private String matricule;
        private String grade;

    }

