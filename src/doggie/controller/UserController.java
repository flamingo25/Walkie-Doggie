package doggie.controller;

import java.io.OutputStream;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import doggie.user.dao.UserDao;
import doggie.user.dao.UserImageDao;
import doggie.user.model.User;
import doggie.user.model.UserImage;
import doggie.user.model.UserProfile;

@Controller
public class UserController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserImageDao userImageDao;

	@RequestMapping(value = { "/userProfile" })
	public String userProfile(Model model, Principal principal) {
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);
		
		model.addAttribute("user", user);
		
		return "userProfile";
	}
	
	@RequestMapping(value = { "/editProfile" }, method = RequestMethod.GET)
	public String showEditProfile(Model model, Principal principal) {
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);
		
		if (user.getUserProfile() == null) {
			user.setUserProfile(new UserProfile());
		}

		model.addAttribute("user", user);
		
		return "editProfile";
	}
	
	@RequestMapping(value = { "/editProfile" }, method = RequestMethod.POST)
	public String editProfile(@Valid UserProfile newUserProfile,
			Model model, Principal principal, BindingResult bindingResult,
			@RequestParam("date") String date) {
		
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/petbook";
		}
		
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);
		
		if (user.getUserProfile() == null) {
			user.setUserProfile(new UserProfile());
		}
		
		UserProfile profile = user.getUserProfile();
		profile.setFirstName(newUserProfile.getFirstName());
		profile.setSurName(newUserProfile.getSurName());
		profile.setEmail(newUserProfile.getEmail());
		profile.setZip(newUserProfile.getZip());
		profile.setCity(newUserProfile.getCity());
		profile.setAddress(newUserProfile.getAddress());
		profile.setPhone(newUserProfile.getPhone());
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {				
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}
		Date dayOfBirth = cal.getTime();
		profile.setDayOfBirth(dayOfBirth);
		
		userDao.save(user);
		
		model.addAttribute("message", "Changed User Profile " + user.getUserName());
		
		return "forward:/userProfile";
	}
	
	@RequestMapping(value = "/userUpload", method = RequestMethod.GET)
	public String showUserUploadForm(Model model, Principal principal) {
		List<User> userOpt = userDao.findByUserName(principal.getName());
		User user = userOpt.get(0);

		model.addAttribute("user", user);
		return "uploadUserImage";
	}

	@RequestMapping(value = "/userUpload", method = RequestMethod.POST)
	public String uploadUserImage(Model model, Principal principal,
			@RequestParam("myFile") MultipartFile file) {

		try {
			List<User> userOpt = userDao.findByUserName(principal.getName());
			User user = userOpt.get(0);	
			UserProfile profile = user.getUserProfile();
			
			if (profile.getImage() != null) {
				userImageDao.delete(profile.getImage());
				profile.setImage(null);
			}

			UserImage image = new UserImage();
			image.setContent(file.getBytes());
			image.setContentType(file.getContentType());
			image.setFilename(file.getOriginalFilename());
			image.setName(file.getName());
			image.setUserProfile(profile);
			profile.setImage(image);
			user.setUserProfile(profile);
			userDao.save(user);

		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}

		return "forward:/userProfile";
	}

	@RequestMapping("/userImage")
	public void downloadUser(@RequestParam("id") int imageId, HttpServletResponse response) {

		Optional<UserImage> imgOpt = userImageDao.findById(imageId);
		if (!imgOpt.isPresent())
			throw new IllegalArgumentException("No image with id " + imageId);

		UserImage img = imgOpt.get();

		try {
			OutputStream out = response.getOutputStream();
			response.setContentType(img.getContentType());
			out.write(img.getContent());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}
}
