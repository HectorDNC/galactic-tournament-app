package tech.galactictournament.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SpecieRequestDTO {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "powerLevel is required")
    @Positive(message = "powerLevel must be positive")
    private Integer powerLevel;

    @NotBlank(message = "specialAbility is required")
    @Size(max = 250, message = "specialAbility must be at most 250 characters")
    private String specialAbility;
}
