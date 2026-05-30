package tech.galactictournament.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.galactictournament.domain.entities.Battle;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

    @Query("SELECT b FROM Battle b ORDER BY b.battleDate DESC LIMIT 1")
    Optional<Battle> findLastBattle();
}
