package tech.galactictournament.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.galactictournament.domain.entities.BattleStatistics;
import tech.galactictournament.domain.entities.Specie;

@Repository
public interface BattleStatisticsRepository extends JpaRepository<BattleStatistics, Long> {

    Optional<BattleStatistics> findBySpecies(Specie species);
}
