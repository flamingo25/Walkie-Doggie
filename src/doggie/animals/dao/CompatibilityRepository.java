package doggie.animals.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AC;

@Repository
@Transactional
public interface CompatibilityRepository extends JpaRepository<AC, Integer> {

}
