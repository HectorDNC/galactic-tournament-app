package tech.galactictournament.domain.dtos;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BattleDTO {

    private Long id;
    private SpecieDTO fighterLeft;
    private SpecieDTO fighterRight;
    private SpecieDTO winner;
    private LocalDateTime battleDate;
}
