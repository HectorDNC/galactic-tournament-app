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

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.SpecieDTO;
import tech.galactictournament.domain.dtos.SpecieRequestDTO;
import tech.galactictournament.domain.services.SpecieService;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
@Tag(name = "Especies", description = "Endpoints para gestionar especies")
public class SpecieController {

    private final SpecieService specieService;

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las especies")
    @ApiResponse(responseCode = "200", description = "Lista de todas las especies obtenida exitosamente")
    public ResponseEntity<List<SpecieDTO>> getAll() {
        return ResponseEntity.ok(specieService.findAll());
    }

    @GetMapping
    @Operation(summary = "Obtener especies con paginación")
    @ApiResponse(responseCode = "200", description = "Página de especies obtenida exitosamente")
    public ResponseEntity<Page<SpecieDTO>> getPaginated(
            @Parameter(description = "Número de página (0-indexed)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Filtro por nombre de especie")
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(specieService.findPaginated(page, size, name));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una especie por ID")
    @ApiResponse(responseCode = "200", description = "Especie obtenida exitosamente")
    public ResponseEntity<SpecieDTO> getById(
            @Parameter(description = "ID de la especie", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(specieService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva especie")
    @ApiResponse(responseCode = "201", description = "Especie creada exitosamente")
    public ResponseEntity<SpecieDTO> create(@Valid @RequestBody SpecieRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una especie existente")
    @ApiResponse(responseCode = "200", description = "Especie actualizada exitosamente")
    public ResponseEntity<SpecieDTO> update(
            @Parameter(description = "ID de la especie", example = "1")
            @PathVariable Long id, @Valid @RequestBody SpecieRequestDTO dto) {
        return ResponseEntity.ok(specieService.update(id, dto));
    }

}

