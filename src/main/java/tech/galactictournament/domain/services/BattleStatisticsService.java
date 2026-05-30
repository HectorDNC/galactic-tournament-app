package tech.galactictournament.domain.services;

import java.util.List;

import tech.galactictournament.domain.dtos.BattleStatisticsDTO;

public interface BattleStatisticsService {

    List<BattleStatisticsDTO> getRanking();
}
