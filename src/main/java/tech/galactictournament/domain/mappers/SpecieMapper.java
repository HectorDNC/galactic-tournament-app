package tech.galactictournament.domain.mappers;

import org.springframework.stereotype.Component;

import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.entities.Specie;

@Component
public class SpecieMapper {

    public SpecieDTO toDTO(Specie specie) {
        SpecieDTO dto = new SpecieDTO();
        dto.setId(specie.getId());
        dto.setName(specie.getName());
        dto.setPowerLevel(specie.getPowerLevel());
        dto.setSpecialAbility(specie.getSpecialAbility());
        dto.setCreatedAt(specie.getCreatedAt());
        return dto;
    }
}
