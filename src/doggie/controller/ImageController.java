package doggie.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import doggie.animals.dao.AnimalRepository;
import doggie.animals.dao.ImageRepository;
import doggie.animals.model.AnimalImage;
import doggie.animals.model.AnimalModel;

@Controller
public class ImageController {

	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	ImageRepository imageRepository;

	@RequestMapping(value = "/animal/upload", method = RequestMethod.GET)
	public String showUploadForm(Model model, @RequestParam("id") int animalId) {
		model.addAttribute("animalId", animalId);
		return "/animal/upload";
	}

	@RequestMapping(value = "/animal/upload", method = RequestMethod.POST)
	public String uploadDocument(Model model, @RequestParam("id") int animalId,
			@RequestParam("myFile") MultipartFile file) {

		try {

			Optional<AnimalModel> animalOpt = animalRepository.findById(animalId);
			if (!animalOpt.isPresent())
				throw new IllegalArgumentException("No animal with id " + animalId);

			AnimalModel animal = animalOpt.get();

			AnimalImage image = new AnimalImage();
			image.setContent(file.getBytes());
			image.setContentType(file.getContentType());
			image.setFilename(file.getOriginalFilename());
			image.setName(file.getOriginalFilename());
			if (CollectionUtils.isEmpty(imageRepository.findAllByAnimalAndProfile(animal, true)))
			image.setProfile(true); else image.setProfile(false);
			image.setAnimal(animal);
			animal.addImage(image);
			imageRepository.save(image);

		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}

		return "forward:/animal/profile";
	}

	@RequestMapping("/animal/image")
	public void download(@RequestParam("id") int imageId, HttpServletResponse response) {

		Optional<AnimalImage> imgOpt = imageRepository.findById(imageId);
		if (!imgOpt.isPresent())
			throw new IllegalArgumentException("No image with id " + imageId);

		AnimalImage img = imgOpt.get();

		try {
			OutputStream out = response.getOutputStream();
			response.setContentType(img.getContentType());
			out.write(img.getContent());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/animal/profileImage")
	public void downloadProfile(@RequestParam("id") int animalId, HttpServletResponse response) {
		
		Optional<AnimalModel> animalOpt = animalRepository.findById(animalId);
		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + animalId);

		AnimalModel animal = animalOpt.get();

		List<AnimalImage> imgOpt = imageRepository.findAllByAnimalAndProfile(animal, true);
		AnimalImage img = imgOpt.get(0);

		try {
			OutputStream out = response.getOutputStream();
			response.setContentType(img.getContentType());
			out.write(img.getContent());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/animal/images")
	public String images(Model model, @RequestParam int id) {
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();
		model.addAttribute("animal", animal);

		List<AnimalImage> images = imageRepository.findAllByAnimal(animal);
		model.addAttribute("images", images);

		return "/animal/images";
	}

	@RequestMapping(value = "/animal/images/update")
	public String updateImage(Model model, @RequestParam int id, @RequestParam String description) {

		Optional<AnimalImage> imageOpt = imageRepository.findById(id);

		if (!imageOpt.isPresent())
			throw new IllegalArgumentException("No Image with id " + id);

		AnimalImage image = imageOpt.get();
		image.setName(description);
		imageRepository.save(image);
		
		return "redirect:/animal/images?id=" + image.getAnimal().getId();
	}

	@RequestMapping(value = "/animal/images/delete")
	public String deleteImage(Model model, @RequestParam int id) {

		Optional<AnimalImage> imageOpt = imageRepository.findById(id);

		if (!imageOpt.isPresent())
			throw new IllegalArgumentException("No Image with id " + id);
		
		AnimalImage image = imageOpt.get();
		AnimalModel animal = image.getAnimal();
		
		imageRepository.deleteById(id);
		
		if (image.isProfile()) {		
		AnimalImage newProfile = imageRepository.findAllByAnimal(animal).get(0);
		newProfile.setProfile(true);
		imageRepository.save(newProfile);
		}

		return "redirect:/animal/images?id=" + image.getAnimal().getId();
	}

	@RequestMapping(value = "/animal/images/profile")
	public String profileImage(Model model, @RequestParam int id) {

		Optional<AnimalImage> imageOpt = imageRepository.findById(id);

		if (!imageOpt.isPresent())
			throw new IllegalArgumentException("No Image with id " + id);

		AnimalImage image = imageOpt.get();
		AnimalModel animal = image.getAnimal();
		Set<AnimalImage> images = animal.getImage();
		images.forEach(i -> i.setProfile(false));
		image.setProfile(true);
		animal.setImage(images);
		animalRepository.save(animal);

		return "redirect:/animal/images?id=" + animal.getId();
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}

}
