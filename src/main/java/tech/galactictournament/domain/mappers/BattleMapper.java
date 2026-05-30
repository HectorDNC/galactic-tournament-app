package tech.galactictournament.domain.mappers;

import org.springframework.stereotype.Component;

import tech.galactictournament.domain.dtos.BattleDTO;
import tech.galactictournament.domain.entities.Battle;

@Component
public class BattleMapper {

    private final SpecieMapper specieMapper;

    public BattleMapper(SpecieMapper specieMapper) {
        this.specieMapper = specieMapper;
    }

    public BattleDTO toDTO(Battle battle) {
        if (battle == null) return null;
        BattleDTO dto = new BattleDTO();
        dto.setId(battle.getId());
        dto.setFighterLeft(specieMapper.toDTO(battle.getFighterLeft()));
        dto.setFighterRight(specieMapper.toDTO(battle.getFighterRight()));
        dto.setWinner(specieMapper.toDTO(battle.getWinner()));
        dto.setBattleDate(battle.getBattleDate());
        return dto;
    }
}
