package doggie.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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

	@PostConstruct
	@Transactional
	public void init() {

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

	@RequestMapping(value = { "/signUp" }, method = RequestMethod.GET)
	public String signUp() {
		return "signUp";
	}


	@RequestMapping(value = { "/signUp" }, method = RequestMethod.POST)
	public String signUp(@Valid UserProfile newUserProfile, Model model, 
			@RequestParam("date") String date,
			@RequestParam("username") String username,
			@RequestParam("password") String password1,
			@RequestParam("password2") String password2) {
		
		List<User> users = userDao.findByUserName(username);
		if (CollectionUtils.isEmpty(users)) {
			if (password1.equals(password2)) {
			User user = new User(username, password1, true);
			user.encryptPassword();
			user.addUserRole(userRoleDao.findByRole("ROLE_USER"));
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				cal.setTime(sdf.parse(date));
			} catch (ParseException e) {				
				model.addAttribute("errorMessage", "Error:" + e.getMessage());
			}
			Date dayOfBirth = cal.getTime();
			newUserProfile.setDayOfBirth(dayOfBirth);
				
			
			user.setUserProfile(newUserProfile);
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
		
}
