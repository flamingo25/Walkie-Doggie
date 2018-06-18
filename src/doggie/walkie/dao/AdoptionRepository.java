package doggie.walkie.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalModel;
import doggie.user.model.User;
import doggie.walkie.model.AdoptionModel;

@Repository
@Transactional
public interface AdoptionRepository extends JpaRepository<AdoptionModel, Integer> {

	List<AdoptionModel> findByUserAndAnimal(User user, AnimalModel animal);
}
