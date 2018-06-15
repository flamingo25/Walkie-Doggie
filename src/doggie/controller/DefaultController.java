package doggie.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import doggie.user.model.User;

@Controller
public class DefaultController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AnimalRepository animalRepository;
		
	@RequestMapping(value = "/")
	public String index(Model model, Principal principal) {
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);
		
		List<AnimalModel> favourites = animalRepository.findAllByFUser(user);
		
		model.addAttribute("favourites", favourites);
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/favourite")
	public String favourite(Model model, Principal principal,
			@RequestParam int id,
			@RequestParam(required = false, name = "rm") boolean rm) {
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);
		
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();
		
		if (rm != true) {
			List<AnimalModel> animalOp = user.getFavourites().stream().filter(a -> a.getId() == animal.getId()).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(animalOp)) {
				user.addFavourite(animal);
				userDao.save(user);
				model.addAttribute("message", "Animal " + animal.getName() + " added to favourites");
			} else model.addAttribute("message", "Animal " + animal.getName() + " is already favourites");
			
			return "forward:/animal/profile";
		} else {
			user.getFavourites().removeIf(a -> a.getId() == animal.getId());
			userDao.save(user);
			model.addAttribute("errorMessage", "Animal " + animal.getName() + " removed from favourites");
			
			return "forward:/";
		}
		
		
		

	}
}
