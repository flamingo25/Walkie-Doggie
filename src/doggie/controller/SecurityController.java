package doggie.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import doggie.user.dao.UserDao;
import doggie.user.dao.UserRoleDao;
import doggie.user.model.User;
import doggie.user.model.UserProfile;
import doggie.user.model.UserRole;

@Controller
public class SecurityController {

	@Autowired
	UserDao userDao;

	@Autowired
	UserRoleDao userRoleDao;

	@RequestMapping("/fillUsers")
	@Transactional
	public String fillData(Model model) {

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

		return "forward:login";
	}

	@RequestMapping(value = { "/signUp" }, method = RequestMethod.GET)
	public String signUp() {
		return "signUp";
	}


	@RequestMapping(value = { "/signUp" }, method = RequestMethod.POST)
	public String signUp(Model model, 
			@RequestParam("username") String username,
			@RequestParam("password") String password1,
			@RequestParam("password2") String password2) {
		
		List<User> users = userDao.findByUserName(username);
		if (CollectionUtils.isEmpty(users)) {
			if (password1.equals(password2)) {
			User user = new User(username, password1, true);
			user.encryptPassword();
			user.addUserRole(userRoleDao.findByRole("ROLE_USER"));
			userDao.save(user);
			model.addAttribute("message", "User " + username + " created!");
			} else {
				model.addAttribute("errorMessage", "Error: Passwords doesn't match!");
			}
		} else {
			model.addAttribute("errorMessage", "Error: " + username + " already exists!");
		}
		return "signUp";
	}
	
	
	@RequestMapping(value = { "/addProfile" })
	public String addProfile(Model model) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(1990, 04, 13);
		
		Date date = cal.getTime();
		
		Optional<User> userOpt = userDao.findById(1);
		
		if (!userOpt.isPresent())
			throw new IllegalArgumentException("No user with id");
		
		User user = userOpt.get();
		UserProfile userprofile = new UserProfile("admin", "adminson", date, "admin@doggie.at", 8010, "Graz", "Alte Poststraﬂe 123", "0123 / 456 78 90");
		user.setUserProfile(userprofile);
		userDao.save(user);
		
		

		return "petbook";
	}
	
	

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}
