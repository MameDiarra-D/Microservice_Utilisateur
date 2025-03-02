package uasz.sn.microservice_utilisateur.mapper;

import org.mapstruct.Mapper;
import uasz.sn.microservice_utilisateur.dto.PermanantDTO;
import uasz.sn.microservice_utilisateur.modele.Permanant;

import java.util.List;
@Mapper
public interface PermanantMapper {

   PermanantDTO permanantToDTO(Permanant permanant);

   Permanant dtoToPermanant( PermanantDTO permanantDTO);

   List<PermanantDTO> permanantToDTOs(List<Permanant> permanants);
}

