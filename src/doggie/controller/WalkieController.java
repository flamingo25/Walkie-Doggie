package doggie.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import doggie.animals.dao.AnimalRepository;
import doggie.animals.model.AnimalModel;
import doggie.user.dao.UserDao;
import doggie.user.dao.UserRoleDao;
import doggie.user.model.User;
import doggie.user.model.UserProfile;
import doggie.user.model.UserRole;
import doggie.walkie.dao.AdoptionRepository;
import doggie.walkie.dao.CalendarRepository;
import doggie.walkie.model.AdoptionModel;
import doggie.walkie.model.CalendarModel;

@Controller
public class WalkieController {

	@Autowired
	CalendarRepository calendarRepository;

	@Autowired
	UserDao userDao;

	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	AdoptionRepository adoptionRepository;

	@Autowired
	UserRoleDao userRoleDao;

	@RequestMapping(value = "/calendar/animal")
	public String animalCalendar(Model model, @RequestParam int id) {
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();

		List<CalendarModel> events = calendarRepository.findAllByAnimal(animal);

		model.addAttribute("events", events);
		model.addAttribute("animal", animal);

		return "/walkie/calendar";
	}

	@RequestMapping(value = "/calendar/user")
	public String userCalendar(Model model, Principal principal) {
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);

		if (user.getUserProfile() == null) {
			user.setUserProfile(new UserProfile());
		}

		List<CalendarModel> events = calendarRepository.findAllByUser(user);

		model.addAttribute("events", events);

		return "/walkie/calendar";
	}

	@RequestMapping(value = "/calendar/addEvent", method = RequestMethod.GET)
	public String showAddEvent(Model model, @RequestParam String date, @RequestParam int id) {
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();

		model.addAttribute("animal", animal);
		model.addAttribute("date", date);

		return "/walkie/add";
	}

	@RequestMapping(value = "/calendar/addEvent", method = RequestMethod.POST)
	public String addEvent(Model model, Principal principal, @RequestParam String date, @RequestParam int id) {
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();

		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}
		Date date1 = cal.getTime();
		if (CollectionUtils.isEmpty(calendarRepository.findAllByEventAndAnimal(date1, animal))) {

			CalendarModel event = new CalendarModel();
			event.setEvent(cal.getTime());
			event.setAnimal(animal);
			event.setUser(user);
			calendarRepository.save(event);
			model.addAttribute("message", "Termin für " + animal.getName() + " wurde hinzugefügt!");
		} else
			model.addAttribute("errorMessage", animal.getName() + " hat an diesem Tag bereits einen Termin!");

		return "forward:/calendar/animal";
	}

	@RequestMapping(value = "/adopt/new", method = RequestMethod.GET)
	public String showAdopt(Model model, @RequestParam int id) {
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();

		model.addAttribute("animal", animal);

		return "/walkie/adopt";
	}

	@RequestMapping(value = "/adopt/new", method = RequestMethod.POST)
	public String adopt(Model model, Principal principal, @RequestParam int id) {

		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);

		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();

		Date date = new Date();

		if (CollectionUtils.isEmpty(adoptionRepository.findByUserAndAnimal(user, animal))) {

			AdoptionModel adopt = new AdoptionModel(date, false, false);
			adopt.setAnimal(animal);
			adopt.setUser(user);

			adoptionRepository.save(adopt);
			model.addAttribute("message", "Adoption wurde beantragt!");
		} else
			model.addAttribute("errorMessage", "Es wurde bereits eine Adoption beantragt!");

		return "forward:/animal/profile";
	}

	@RequestMapping(value = { "/adopt/adoption", "/adopt/" })
	public String animalCalendar(Model model, Principal principal) {

		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);

		UserRole role = userRoleDao.findByRole("ROLE_EMPLOYEE");

		List<User> userOpti = userDao.findByIdAndUserRoles(user.getId(), role);
		if (CollectionUtils.isEmpty(userOpti)) {
			List<AdoptionModel> adoptions = adoptionRepository.findByUser(user);

			model.addAttribute("adoptions", adoptions);
		} else {
			List<AdoptionModel> adoptions = adoptionRepository.findAll();

			model.addAttribute("adoptions", adoptions);
		}
		return "/walkie/adoption";
	}

	@RequestMapping(value = "/adopt/accept")
	public String accept(Model model, @RequestParam int id, @RequestParam boolean accept) {

		Optional<AdoptionModel> adoptionOpt = adoptionRepository.findById(id);

		if (!adoptionOpt.isPresent())
			throw new IllegalArgumentException("No adoption with id " + id);

		AdoptionModel adoption = adoptionOpt.get();

		if (!adoption.isProcessed()) {
			adoption.setProcessed(true);

			if (accept) {
				List<AdoptionModel> adoptions = adoptionRepository.findAllByAnimal(adoption.getAnimal());
				adoptions.removeIf(v -> v.getId() == adoption.getId());
				adoptions.forEach(v -> v.setAccepted(false));
				adoptions.forEach(v -> v.setProcessed(true));
				adoption.setAccepted(true);
				adoptions.add(adoption);
				
				adoptionRepository.saveAll(adoptions);
				

				model.addAttribute("message", "Antrag " + id + " wurde akzeptiert!");
				

			} else {
				adoption.setAccepted(false);
				model.addAttribute("errorMessage", "Antrag " + id + " wurde abgelehnt!");
				adoptionRepository.save(adoption);
			}


		} else {
			model.addAttribute("errorMessage", "Antrag " + id + " wurde bereits bearbeitet!");
		}

		return "forward:/adopt/adoption";
	}

}
