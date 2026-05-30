package tech.galactictournament.domain.dtos;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SpecieDTO {
    private Long id;
    private String name;
    private Integer powerLevel;
    private String specialAbility;
    private LocalDateTime createdAt;
}
