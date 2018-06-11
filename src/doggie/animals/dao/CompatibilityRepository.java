package doggie.animals.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalModel;
import doggie.animals.model.Compatibility;

@Repository
@Transactional
public interface CompatibilityRepository extends JpaRepository<Compatibility, Integer> {

	List<Compatibility> findAllByAnimals(AnimalModel animal);
	
	List<Compatibility> findByIdIn(List<Integer> ids);
}
	