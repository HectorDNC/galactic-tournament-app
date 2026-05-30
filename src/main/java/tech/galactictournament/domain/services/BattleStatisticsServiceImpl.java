package tech.galactictournament.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.BattleStatisticsDTO;
import tech.galactictournament.domain.mappers.BattleStatisticsMapper;
import tech.galactictournament.domain.repositories.BattleStatisticsRepository;

@Service
@RequiredArgsConstructor
public class BattleStatisticsServiceImpl implements BattleStatisticsService {

    private final BattleStatisticsRepository statsRepository;
    private final BattleStatisticsMapper statsMapper;

    @Override
    public List<BattleStatisticsDTO> getRanking() {
        return statsRepository.findAllOrderedByVictoriesDescAndNameAsc().stream()
                .map(statsMapper::toDTO)
                .toList();
    }
}
