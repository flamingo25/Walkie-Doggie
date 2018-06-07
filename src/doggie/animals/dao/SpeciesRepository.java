package doggie.animals.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.Species;

@Repository
@Transactional
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

}