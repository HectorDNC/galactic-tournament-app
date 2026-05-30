package tech.galactictournament.domain.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.entities.Specie;
import tech.galactictournament.domain.repositories.SpecieRepository;

@Service
@RequiredArgsConstructor
public class SpecieService {

    private final SpecieRepository specieRepository;

    public List<SpecieDTO> findAll() {
        return specieRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public SpecieDTO create(SpecieDTO dto) {
        Specie specie = new Specie();
        specie.setName(dto.getName());
        specie.setPowerLevel(dto.getPowerLevel());
        specie.setSpecialAbility(dto.getSpecialAbility());
        specie.setCreatedAt(LocalDateTime.now());

        return toDTO(specieRepository.save(specie));
    }

    private SpecieDTO toDTO(Specie specie) {
        SpecieDTO dto = new SpecieDTO();
        dto.setId(specie.getId());
        dto.setName(specie.getName());
        dto.setPowerLevel(specie.getPowerLevel());
        dto.setSpecialAbility(specie.getSpecialAbility());
        dto.setCreatedAt(specie.getCreatedAt());
        return dto;
    }
}
