package doggie.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import doggie.user.dao.UserDao;
import doggie.user.dao.UserRoleDao;
import doggie.user.model.User;
import doggie.user.model.UserProfile;

@Controller
public class SecurityController {

	@Autowired
	UserDao userDao;

	@Autowired
	UserRoleDao userRoleDao;

	@RequestMapping(value = { "/signUp" }, method = RequestMethod.GET)
	public String signUp() {
		return "signUp";
	}

	@RequestMapping(value = { "/signUp" }, method = RequestMethod.POST)
	public String signUp(@Valid UserProfile newUserProfile, BindingResult bindingResult, Model model, 
			@RequestParam("date") String date,
			@RequestParam("username") String username,
			@RequestParam("password") String password1,
			@RequestParam("password2") String password2) {
		
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/petbook";
		}
		
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
			model.addAttribute("message", "User " + username + " wurde erstellt!");
			} else {
				model.addAttribute("errorMessage", "Passwörter stimmen nicht überein!");
			}
		} else {
			model.addAttribute("errorMessage", username + " existiert bereits!");
		}
		return "signUp";
	}
		
}
