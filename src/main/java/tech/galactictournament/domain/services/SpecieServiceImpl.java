package tech.galactictournament.domain.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.config.exceptions.BadRequestException;
import tech.galactictournament.config.exceptions.NotFoundException;
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

    public Page<SpecieDTO> findPaginated(int page, int size, String name) {
        PageRequest pageable = PageRequest.of(page, size);
        if (name != null && !name.isBlank()) {
            return specieRepository.findByFilter(name, pageable)
                    .map(specieMapper::toDTO);
        }
        return specieRepository.findAll(pageable)
                .map(specieMapper::toDTO);
    }

    public SpecieDTO findById(Long id) {
        return specieRepository.findById(id)
                .map(specieMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Especie no encontrada"));
    }

    @Override
    public SpecieDTO create(SpecieRequestDTO dto) {
        Specie existingSpecie = specieRepository.findByName(dto.getName());
        if (existingSpecie != null) {
            throw new BadRequestException("Ya existe una especie con ese nombre");
        }

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

