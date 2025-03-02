package uasz.sn.microservice_utilisateur.mapper;

import org.mapstruct.Mapper;
import uasz.sn.microservice_utilisateur.dto.EtudiantDTO;
import uasz.sn.microservice_utilisateur.modele.Etudiant;

import java.util.List;

@Mapper
public interface EtudiantMapper {

 EtudiantDTO etudiantToDTO(Etudiant etudiant);

 Etudiant dtoToEtudiant( EtudiantDTO etudiantDTO);

 List<EtudiantDTO> etudiantToDTOs(List<Etudiant> etudiants);
}

