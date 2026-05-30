package tech.galactictournament.domain.services;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.BattleDTO;
import tech.galactictournament.domain.dtos.BattleRequestDTO;
import tech.galactictournament.domain.entities.Battle;
import tech.galactictournament.domain.mappers.BattleMapper;
import tech.galactictournament.domain.entities.BattleStatistics;
import tech.galactictournament.domain.entities.Specie;
import tech.galactictournament.domain.repositories.BattleRepository;
import tech.galactictournament.domain.repositories.BattleStatisticsRepository;
import tech.galactictournament.domain.repositories.SpecieRepository;

@Service
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService {

    private final SpecieRepository specieRepository;
    private final BattleRepository battleRepository;
    private final BattleStatisticsRepository statsRepository;
    private final BattleMapper battleMapper;

    @Override
    @Transactional
    public BattleDTO startBattle(BattleRequestDTO request) {
        if (request.getFighterLeftId().equals(request.getFighterRightId())) {
            throw new IllegalArgumentException("Una especie no puede combatir contra sí misma");
        }

        Specie left = specieRepository.findById(request.getFighterLeftId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Especie peleadora izquierda no encontrada: " + request.getFighterLeftId()));

        Specie right = specieRepository.findById(request.getFighterRightId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Especie peleadora derecha no encontrada: " + request.getFighterRightId()));

        // Determinar ganador y razón
        Specie winner;
        Specie loser;

        int powerComparison = Integer.compare(left.getPowerLevel(), right.getPowerLevel());

        if (powerComparison > 0) {
            winner = left;
            loser = right;
        } else if (powerComparison < 0) {
            winner = right;
            loser = left;
        } else {
            // Empate: gana la que sea alfabéticamente primera
            int nameComparison = left.getName().compareToIgnoreCase(right.getName());
            if (nameComparison <= 0) {
                winner = left;
                loser = right;
            } else {
                winner = right;
                loser = left;
            }
        }

        // Registrar la batalla
        Battle battle = new Battle();
        battle.setFighterLeft(left);
        battle.setFighterRight(right);
        battle.setWinner(winner);
        battle.setBattleDate(LocalDateTime.now());
        battle = battleRepository.save(battle);

        // Actualizar estadísticas del ganador
        BattleStatistics winnerStats = statsRepository.findBySpecies(winner)
                .orElseGet(() -> newStatistics(winner));
        winnerStats.setVictories(winnerStats.getVictories() + 1);
        statsRepository.save(winnerStats);

        // Actualizar estadísticas del perdedor
        BattleStatistics loserStats = statsRepository.findBySpecies(loser)
                .orElseGet(() -> newStatistics(loser));
        loserStats.setDefeats(loserStats.getDefeats() + 1);
        statsRepository.save(loserStats);

        return battleMapper.toDTO(battle);
    }

    private BattleStatistics newStatistics(Specie specie) {
        BattleStatistics stats = new BattleStatistics();
        stats.setSpecies(specie);
        stats.setVictories(0);
        stats.setDefeats(0);
        stats.setCreatedAt(LocalDateTime.now());
        return stats;
    }

    
}

