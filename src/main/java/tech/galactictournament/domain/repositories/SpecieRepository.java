package tech.galactictournament.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.galactictournament.domain.entities.Specie;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {
    
}
