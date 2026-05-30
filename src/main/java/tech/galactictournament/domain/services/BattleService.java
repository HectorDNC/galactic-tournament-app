package tech.galactictournament.domain.services;

import tech.galactictournament.domain.dtos.BattleDTO;
import tech.galactictournament.domain.dtos.BattleRequestDTO;

public interface BattleService {

    BattleDTO startBattle(BattleRequestDTO request);

    BattleDTO startRandomBattle();
}
