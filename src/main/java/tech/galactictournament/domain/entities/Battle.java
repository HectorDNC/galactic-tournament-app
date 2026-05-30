package tech.galactictournament.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "battles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Battle { // Combates

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fighter_left_id", nullable = false)
    private Specie fighterLeft;

    @ManyToOne
    @JoinColumn(name = "fighter_right_id", nullable = false)
    private Specie fighterRight;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Specie winner;

    @Column(name = "date")
    private LocalDateTime battleDate;

}
