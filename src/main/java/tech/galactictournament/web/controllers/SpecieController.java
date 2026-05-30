package tech.galactictournament.web.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.SpecieDTO;
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

    @PostMapping
    public ResponseEntity<SpecieDTO> create(@RequestBody SpecieDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.create(dto));
    }
}
