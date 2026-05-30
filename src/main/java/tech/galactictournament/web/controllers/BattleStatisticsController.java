package tech.galactictournament.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.BattleStatisticsDTO;
import tech.galactictournament.domain.services.BattleStatisticsService;

@RestController
@RequestMapping("/api/battle-statistics")
@RequiredArgsConstructor
public class BattleStatisticsController {

    private final BattleStatisticsService battleStatisticsService;

    @GetMapping("/ranking")
    public ResponseEntity<List<BattleStatisticsDTO>> getRanking() {
        return ResponseEntity.ok(battleStatisticsService.getRanking());
    }
}
