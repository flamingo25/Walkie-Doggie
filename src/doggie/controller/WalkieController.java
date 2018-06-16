package doggie.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import doggie.animals.dao.AnimalRepository;
import doggie.animals.model.AnimalModel;
import doggie.user.dao.UserDao;
import doggie.user.model.User;
import doggie.user.model.UserProfile;
import doggie.walkie.dao.CalendarRepository;
import doggie.walkie.model.CalendarModel;

@Controller
public class WalkieController {

	@Autowired
	CalendarRepository calendarRepository;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AnimalRepository animalRepository;
	
	@RequestMapping(value = "/calendar/animal")
	public String animalCalendar(Model model, @RequestParam int id) {
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();		
		
		List<CalendarModel> events = calendarRepository.findAllByAnimal(animal);
		
		model.addAttribute("events", events);
		
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
}
