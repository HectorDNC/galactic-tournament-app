package tech.galactictournament.web.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.services.SpecieServiceImpl;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpecieController {

    private final SpecieServiceImpl specieService;

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
    public ResponseEntity<SpecieDTO> create(@RequestBody SpecieDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.create(dto));
    }
}
