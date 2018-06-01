package doggie.animals.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalModel;
import doggie.animals.model.TierVerträglichkeit;

@Repository
@Transactional
public interface ACRepository extends JpaRepository<TierVerträglichkeit, Integer> {

	List<TierVerträglichkeit> findAllByTier(AnimalModel animal);
	
}
