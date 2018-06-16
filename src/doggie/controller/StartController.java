package doggie.controller;

import java.util.Calendar;
import java.util.List;

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
import doggie.walkie.dao.CalendarRepository;
import doggie.walkie.model.CalendarModel;

@Controller
public class StartController {
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	CompatibilityRepository compatibilityRepository;

	@Autowired
	VaccinationRepository vaccinationRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	CalendarRepository calendarRepository;
	
	
	@PostConstruct
	@Transactional
	public void initUsers() {

		UserRole adminRole = userRoleDao.findByRole("ROLE_ADMIN");
		if (adminRole == null) {
			adminRole = new UserRole("ROLE_ADMIN");
			userRoleDao.save(adminRole);
		}

		UserRole userRole = userRoleDao.findByRole("ROLE_USER");
		if (userRole == null) {
			userRole = new UserRole("ROLE_USER");
			userRoleDao.save(userRole);
		}
		User admin = new User("admin", "password", true);
		admin.encryptPassword();
		admin.addUserRole(userRole);
		admin.addUserRole(adminRole);
		userDao.save(admin);

		User user = new User("user", "password", true);
		user.encryptPassword();
		user.addUserRole(userRole);
		userDao.save(user);
	}
	
	@PostConstruct
	@Transactional
	public void iniAnimals() {

		Species art1 = new Species("Hund");
		speciesRepository.save(art1);
		Species art2 = new Species("Katze");
		speciesRepository.save(art2);
		Species art3 = new Species("Kleintier");
		speciesRepository.save(art3);
		
		
		Vaccination impfung1 = new Vaccination("impfung1");
		Vaccination impfung2 = new Vaccination("impfung2");
		Vaccination impfung3 = new Vaccination("impfung3");
		Vaccination impfung4 = new Vaccination("impfung4");
		Vaccination impfung5 = new Vaccination("impfung5");
		vaccinationRepository.save(impfung1);
		vaccinationRepository.save(impfung2);
		vaccinationRepository.save(impfung3);
		vaccinationRepository.save(impfung4);
		vaccinationRepository.save(impfung5);
		
		
		Compatibility vt1 = new Compatibility("vt1");
		Compatibility vt2 = new Compatibility("vt2");
		Compatibility vt3 = new Compatibility("vt3");
		Compatibility vt4 = new Compatibility("vt4");
		Compatibility vt5 = new Compatibility("vt5");
		compatibilityRepository.save(vt1);
		compatibilityRepository.save(vt2);
		compatibilityRepository.save(vt3);
		compatibilityRepository.save(vt4);
		compatibilityRepository.save(vt5);

		
		AnimalModel sue = new AnimalModel("Sue", "breed1", "rot", 13, "Weiblich", false, true, "Das ist Sue");
		sue.addVaccination(vaccinationRepository.findById(1).get());
		sue.addVaccination(vaccinationRepository.findById(2).get());
		sue.addCompatibility(compatibilityRepository.findById(1).get());
		sue.addCompatibility(compatibilityRepository.findById(2).get());		
		sue.setSpecies(speciesRepository.findById(1).get());
		
		AnimalModel eli = new AnimalModel("Eliska", "breed2", "blau", 14, "Weiblich", false, true, "Das ist Eliska");
		eli.addVaccination(vaccinationRepository.findById(3).get());
		eli.addVaccination(vaccinationRepository.findById(4).get());
		eli.addCompatibility(compatibilityRepository.findById(3).get());
		eli.addCompatibility(compatibilityRepository.findById(4).get());		
		eli.setSpecies(speciesRepository.findById(2).get());
		
		AnimalModel chris = new AnimalModel("Chris", "breed3", "gelb", 15, "Männlich", false, true, "Das ist Chris");
		chris.addVaccination(vaccinationRepository.findById(5).get());
		chris.addCompatibility(compatibilityRepository.findById(5).get());
		chris.setSpecies(speciesRepository.findById(3).get());
		
		AnimalModel patrik = new AnimalModel("Patrik", "breed4", "grün", 16, "Männlich", false, true, "Das ist Patrik");
		patrik.addVaccination(vaccinationRepository.findById(1).get());
		patrik.addVaccination(vaccinationRepository.findById(2).get());
		patrik.addVaccination(vaccinationRepository.findById(3).get());
		patrik.addCompatibility(compatibilityRepository.findById(1).get());
		patrik.addCompatibility(compatibilityRepository.findById(2).get());
		patrik.addCompatibility(compatibilityRepository.findById(3).get());		
		patrik.setSpecies(speciesRepository.findById(1).get());
		
		AnimalModel tom = new AnimalModel("Thomas", "breed5", "schwarz", 15, "Männlich", false, true, "Das ist Chris");
		tom.addVaccination(vaccinationRepository.findById(3).get());
		tom.addVaccination(vaccinationRepository.findById(4).get());
		tom.addVaccination(vaccinationRepository.findById(5).get());
		tom.addCompatibility(compatibilityRepository.findById(3).get());
		tom.addCompatibility(compatibilityRepository.findById(4).get());
		tom.addCompatibility(compatibilityRepository.findById(5).get());		
		tom.setSpecies(speciesRepository.findById(2).get());
		
		animalRepository.save(sue);
		animalRepository.save(eli);
		animalRepository.save(chris);
		animalRepository.save(patrik);
		animalRepository.save(tom);
	}
	
	@PostConstruct
	@Transactional
	public void iniEvents() {
		
		List<AnimalModel> animals = animalRepository.findAll();
		List<User> users = userDao.findAll();
		
		CalendarModel event1 = new CalendarModel();	
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 05, 18);
		event1.setEvent(cal.getTime());
		event1.setUser(users.get(0));
		event1.setAnimal(animals.get(0));
		calendarRepository.save(event1);
		
		CalendarModel event2 = new CalendarModel();	
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 05, 18);
		event2.setEvent(cal1.getTime());
		event2.setUser(users.get(0));
		event2.setAnimal(animals.get(0));
		calendarRepository.save(event2);
		
		CalendarModel event3 = new CalendarModel();	
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2018, 05, 18);
		event3.setEvent(cal2.getTime());
		event3.setUser(users.get(1));
		event3.setAnimal(animals.get(0));
		calendarRepository.save(event3);
		
		CalendarModel event4 = new CalendarModel();	
		Calendar cal3 = Calendar.getInstance();
		cal3.set(2018, 05, 18);
		event4.setEvent(cal3.getTime());
		event4.setUser(users.get(1));
		event4.setAnimal(animals.get(0));
		calendarRepository.save(event4);
	}
}
