package tech.galactictournament.web.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.dtos.SpecieRequestDTO;
import tech.galactictournament.domain.services.SpecieService;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpecieController {

    private final SpecieService specieService;

    @GetMapping("/all")
    public ResponseEntity<List<SpecieDTO>> getAll() {
        return ResponseEntity.ok(specieService.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<SpecieDTO>> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(specieService.findPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecieDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(specieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SpecieDTO> create(@Valid @RequestBody SpecieRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecieDTO> update(@PathVariable Long id, @Valid @RequestBody SpecieRequestDTO dto) {
        return ResponseEntity.ok(specieService.update(id, dto));
    }

}
