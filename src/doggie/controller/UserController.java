package doggie.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import doggie.user.dao.UserDao;
import doggie.user.model.User;
import doggie.user.model.UserProfile;

@Controller
public class UserController {

	@Autowired
	UserDao userDao;

	@RequestMapping(value = { "/userProfile" })
	public String signUp(Model model, Principal principal) {
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);
		
		if (user.getUserProfile() == null) {
			user.setUserProfile(new UserProfile());
		}

		model.addAttribute("user", user);
		
		
		
		return "userProfile";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}
}
