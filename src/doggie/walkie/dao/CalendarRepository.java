package doggie.walkie.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doggie.animals.model.AnimalModel;
import doggie.user.model.User;
import doggie.walkie.model.CalendarModel;

@Repository
@Transactional
public interface CalendarRepository extends JpaRepository<CalendarModel, Integer> {

	List<CalendarModel> findAllByAnimal(AnimalModel animal);
	
	List<CalendarModel> findAllByUser(User user);

	List<CalendarModel> findAllByEventAndAnimal(Date date, AnimalModel animal);
}
