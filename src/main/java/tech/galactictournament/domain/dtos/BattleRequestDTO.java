package tech.galactictournament.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BattleRequestDTO {

    @NotNull(message = "El ID del luchador izquierdo es obligatorio")
    private Long fighterLeftId;

    @NotNull(message = "El ID del luchador derecho es obligatorio")
    private Long fighterRightId;
}
