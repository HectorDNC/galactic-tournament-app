package tech.galactictournament.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BattleStatisticsDTO {

    private Long id;
    private SpecieDTO species;
    private Integer victories;
    private Integer defeats;
}
