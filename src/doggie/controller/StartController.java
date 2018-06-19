package doggie.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import doggie.animals.dao.AnimalRepository;
import doggie.animals.dao.CompatibilityRepository;
import doggie.animals.dao.ImageRepository;
import doggie.animals.dao.SpeciesRepository;
import doggie.animals.dao.VaccinationRepository;
import doggie.animals.model.AnimalModel;
import doggie.animals.model.Compatibility;
import doggie.animals.model.Species;
import doggie.animals.model.Vaccination;
import doggie.user.dao.UserDao;
import doggie.user.dao.UserRoleDao;
import doggie.user.model.User;
import doggie.user.model.UserRole;
import doggie.walkie.dao.AdoptionRepository;
import doggie.walkie.dao.CalendarRepository;
import doggie.walkie.model.AdoptionModel;
import doggie.walkie.model.CalendarModel;

@Controller
public class StartController {
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Autowired
	UserDao userDao;
	
	@PostConstruct
	@Transactional
	public void initUsers() {
		List<User> users = userDao.findAll();
		users.forEach(v -> v.encryptPassword());
		users.forEach(v -> userDao.save(v));
	}
}
