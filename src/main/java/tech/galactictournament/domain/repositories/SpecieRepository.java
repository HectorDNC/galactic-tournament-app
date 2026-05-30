package tech.galactictournament.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.galactictournament.domain.entities.Specie;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {

    @Query("SELECT s FROM Specie s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Specie> findByFilter(String name, Pageable pageable);

    @Query("SELECT s.id FROM Specie s")
    List<Long> findAllIds();
}
