package tech.galactictournament.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import tech.galactictournament.domain.dtos.BattleDTO;
import tech.galactictournament.domain.dtos.BattleRequestDTO;
import tech.galactictournament.domain.services.BattleService;

@RestController
@RequestMapping("/api/battles")
@RequiredArgsConstructor
@Tag(name = "Combates", description = "Endpoints para gestionar combates entre especies")
public class BattleController {

    private final BattleService battleService;

    @PostMapping
    @Operation(summary = "Iniciar un combate entre dos especies específicas")
    @ApiResponse(responseCode = "201", description = "Combate creado exitosamente")
    public ResponseEntity<BattleDTO> startBattle(@Valid @RequestBody BattleRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(battleService.startBattle(request));
    }

    @PostMapping("/random")
    @Operation(summary = "Iniciar un combate aleatorio entre dos especies")
    @ApiResponse(responseCode = "201", description = "Combate aleatorio creado exitosamente")
    public ResponseEntity<BattleDTO> randomBattle() {
        return ResponseEntity.status(HttpStatus.CREATED).body(battleService.startRandomBattle());
    }
    
}

