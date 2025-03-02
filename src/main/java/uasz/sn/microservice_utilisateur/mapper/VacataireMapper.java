package uasz.sn.microservice_utilisateur.mapper;

import org.mapstruct.Mapper;
import uasz.sn.microservice_utilisateur.dto.VacataireDTO;
import uasz.sn.microservice_utilisateur.modele.Vacataire;

import java.util.List;
@Mapper
public interface VacataireMapper {

    VacataireDTO vacataireToDTO(Vacataire vacataire);

    Vacataire dtoToVacataire( VacataireDTO vacataireDTO);

    List<VacataireDTO> vacataireToDTOs(List<Vacataire> vacataires);
}

