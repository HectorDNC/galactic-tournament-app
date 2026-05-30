package tech.galactictournament.domain.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.dtos.SpecieRequestDTO;
import tech.galactictournament.domain.entities.Specie;
import tech.galactictournament.domain.mappers.SpecieMapper;
import tech.galactictournament.domain.repositories.SpecieRepository;

@Service
@RequiredArgsConstructor
public class SpecieServiceImpl implements SpecieService {

    private final SpecieRepository specieRepository;
    private final SpecieMapper specieMapper;

    public List<SpecieDTO> findAll() {
        return specieRepository.findAll().stream()
                .map(specieMapper::toDTO)
                .toList();
    }

    public Page<SpecieDTO> findPaginated(int page, int size) {
        return specieRepository.findAll(PageRequest.of(page, size))
                .map(specieMapper::toDTO);
    }

    public SpecieDTO findById(Long id) {
        return specieRepository.findById(id)
                .map(specieMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Especie no encontrada"));
    }

    @Override
    public SpecieDTO create(SpecieRequestDTO dto) {
        Specie specie = new Specie();
        specie.setName(dto.getName());
        specie.setPowerLevel(dto.getPowerLevel());
        specie.setSpecialAbility(dto.getSpecialAbility());
        specie.setCreatedAt(LocalDateTime.now());

        return specieMapper.toDTO(specieRepository.save(specie));
    }

    @Override
    public SpecieDTO update(Long id, SpecieRequestDTO dto) {
        Specie specie = specieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Especie no encontrada"));
        if (dto.getName() != null) {
            specie.setName(dto.getName());
        }
        if (dto.getPowerLevel() != null) {
            specie.setPowerLevel(dto.getPowerLevel());
        }
        if (dto.getSpecialAbility() != null) {
            specie.setSpecialAbility(dto.getSpecialAbility());
        }

        return specieMapper.toDTO(specieRepository.save(specie));
    }
}

