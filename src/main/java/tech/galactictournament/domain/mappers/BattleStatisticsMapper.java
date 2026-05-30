package tech.galactictournament.domain.mappers;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tech.galactictournament.domain.dtos.BattleStatisticsDTO;
import tech.galactictournament.domain.entities.BattleStatistics;

@Component
@RequiredArgsConstructor
public class BattleStatisticsMapper {

    private final SpecieMapper specieMapper;

    public BattleStatisticsDTO toDTO(BattleStatistics stats) {
        if (stats == null) return null;
        BattleStatisticsDTO dto = new BattleStatisticsDTO();
        dto.setId(stats.getId());
        dto.setSpecies(specieMapper.toDTO(stats.getSpecies()));
        dto.setVictories(stats.getVictories());
        dto.setDefeats(stats.getDefeats());
        return dto;
    }
}
