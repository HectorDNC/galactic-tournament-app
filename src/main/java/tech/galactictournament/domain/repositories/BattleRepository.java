package tech.galactictournament.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.galactictournament.domain.entities.Battle;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

}
