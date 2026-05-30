package tech.galactictournament.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.dtos.SpecieRequestDTO;

public interface SpecieService {

    List<SpecieDTO> findAll();

    Page<SpecieDTO> findPaginated(int page, int size, String name);

    SpecieDTO findById(Long id);
    
    SpecieDTO create(SpecieRequestDTO dto);

    SpecieDTO update(Long id, SpecieRequestDTO dto);
}
