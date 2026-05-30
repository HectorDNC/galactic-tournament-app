package tech.galactictournament.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.galactictournament.domain.dtos.BattleDTO;
import tech.galactictournament.domain.dtos.BattleRequestDTO;
import tech.galactictournament.domain.services.BattleService;

@RestController
@RequestMapping("/api/battles")
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    @PostMapping
    public ResponseEntity<BattleDTO> startBattle(@Valid @RequestBody BattleRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(battleService.startBattle(request));
    }
}
