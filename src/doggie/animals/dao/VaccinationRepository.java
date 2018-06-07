package doggie.animals.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalModel;
import doggie.animals.model.Vaccination;

@Repository
@Transactional
public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {

	List<Vaccination> findAllByAnimals(AnimalModel animalModel);
		
	List<Vaccination> findByIdIn(List<Integer> ids);
	
}