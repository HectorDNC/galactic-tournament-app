package tech.galactictournament.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import tech.galactictournament.domain.dtos.SpecieDTO;

public interface SpecieService {

    List<SpecieDTO> findAll();

    Page<SpecieDTO> findPaginated(int page, int size);

    SpecieDTO findById(Long id);
}
