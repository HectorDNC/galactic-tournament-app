package tech.galactictournament.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.BattleDTO;
import tech.galactictournament.domain.dtos.BattleStatisticsDTO;
import tech.galactictournament.domain.dtos.DashboardDTO;
import tech.galactictournament.domain.mappers.BattleMapper;
import tech.galactictournament.domain.mappers.BattleStatisticsMapper;
import tech.galactictournament.domain.repositories.BattleRepository;
import tech.galactictournament.domain.repositories.BattleStatisticsRepository;
import tech.galactictournament.domain.repositories.SpecieRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final SpecieRepository specieRepository;
    private final BattleRepository battleRepository;
    private final BattleStatisticsRepository battleStatisticsRepository;
    private final BattleMapper battleMapper;
    private final BattleStatisticsMapper battleStatisticsMapper;

    @Override
    public DashboardDTO getDashboard() {
        long totalSpecies = specieRepository.count();

        List<BattleStatisticsDTO> top3Ranking = battleStatisticsRepository
                .findAllOrderedByVictoriesDescAndNameAsc()
                .stream()
                .limit(3)
                .map(battleStatisticsMapper::toDTO)
                .collect(Collectors.toList());

        BattleDTO lastBattle = battleRepository.findLastBattle()
                .map(battleMapper::toDTO)
                .orElse(null);

        return new DashboardDTO(totalSpecies, top3Ranking, lastBattle);
    }
}
