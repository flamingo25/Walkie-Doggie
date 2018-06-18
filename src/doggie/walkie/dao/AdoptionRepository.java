package doggie.walkie.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.walkie.model.AdoptionModel;

@Repository
@Transactional
public interface AdoptionRepository extends JpaRepository<AdoptionModel, Integer> {

}
