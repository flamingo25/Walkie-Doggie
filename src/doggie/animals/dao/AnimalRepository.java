package doggie.animals.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalModel;
import doggie.user.model.User;

@Repository
@Transactional
public interface AnimalRepository extends JpaRepository<AnimalModel, Integer> {
	
	List<AnimalModel> findAllByFUser(User user);

	List<AnimalModel> findTop3ByOrderByIdDesc();
	
}
