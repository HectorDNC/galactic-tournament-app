package tech.galactictournament.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.BattleStatisticsDTO;
import tech.galactictournament.domain.services.BattleStatisticsService;

@RestController
@RequestMapping("/api/battle-statistics")
@RequiredArgsConstructor
@Tag(name = "Estadísticas de Combates", description = "Endpoints para obtener estadísticas de combates")
public class BattleStatisticsController {

    private final BattleStatisticsService battleStatisticsService;

    @GetMapping("/ranking")
    @Operation(summary = "Obtener ranking de especies por victorias")
    @ApiResponse(responseCode = "200", description = "Ranking obtenido exitosamente")
    public ResponseEntity<List<BattleStatisticsDTO>> getRanking() {
        return ResponseEntity.ok(battleStatisticsService.getRanking());
    }
}
