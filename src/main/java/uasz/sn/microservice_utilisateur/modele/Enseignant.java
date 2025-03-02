package uasz.sn.microservice_utilisateur.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Enseignant extends Utilisateur {
    private String specialite;
    private boolean archive;


}
