package tech.galactictournament.domain.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private long totalSpecies;
    private List<BattleStatisticsDTO> top3Ranking;
    private BattleDTO lastBattle;
}
