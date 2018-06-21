package doggie.animals.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalImage;
import doggie.animals.model.AnimalModel;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<AnimalImage, Integer> {

	List<AnimalImage> findAllByAnimal(AnimalModel animal);

	List<AnimalImage> findAllByAnimalAndProfile(AnimalModel animalModel, boolean profile);
	
	void deleteAllByAnimalId(Integer id);
}
