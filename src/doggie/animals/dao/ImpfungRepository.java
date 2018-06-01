package doggie.animals.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalModel;
import doggie.animals.model.Impfung;

@Repository
@Transactional
public interface ImpfungRepository extends JpaRepository<Impfung, Integer> {

	List<Impfung> findAllByAnimals(AnimalModel animalModel);
}