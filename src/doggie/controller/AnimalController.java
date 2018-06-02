package doggie.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import doggie.animals.dao.ACRepository;
import doggie.animals.dao.AnimalRepository;
import doggie.animals.dao.CompatibilityRepository;
import doggie.animals.dao.ImageRepository;
import doggie.animals.dao.VaccinationRepository;
import doggie.animals.model.AC;
import doggie.animals.model.AnimalImage;
import doggie.animals.model.AnimalModel;
import doggie.animals.model.Compatibility;
import doggie.animals.model.Species;
import doggie.animals.model.Vaccination;

@Controller
public class AnimalController {

	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	CompatibilityRepository compatibilityRepository;

	@Autowired
	VaccinationRepository vaccinationRepository;

	@Autowired
	ACRepository acRepository;

	@Autowired
	ImageRepository imageRepository;

	@RequestMapping(value = { "/fill" })
	public String fillData(Model model) {

		Species art1 = new Species("Hund");
		Vaccination impfung1 = new Vaccination("cool");
		Vaccination impfung2 = new Vaccination("cool2");
		Compatibility vt1 = new Compatibility("vt1");
		Compatibility vt2 = new Compatibility("vt2");

		AnimalModel test = new AnimalModel("a", "b", "c", 12, "a", false, "asdasdas");
		test.addVaccination(impfung1);
		test.addVaccination(impfung2);
		test.setSpecies(art1);

		AC tttt = new AC();
		tttt.setAnimal(test);
		tttt.setCompatibility(vt1);
		tttt.setStatus(true);

		compatibilityRepository.save(tttt);

		return "index";
	}

	@RequestMapping(value = "/petbook")
	public String petbook(Model model) {

		List<AnimalModel> animals = animalRepository.findAll();

		model.addAttribute("animals", animals);

		return "petbook";
	}

	@RequestMapping(value = { "/profil" })
	public String profil(Model model, @RequestParam int id) {
		Optional<AnimalModel> animalOpt = animalRepository.findById(id);

		if (!animalOpt.isPresent())
			throw new IllegalArgumentException("No animal with id " + id);

		AnimalModel animal = animalOpt.get();

		model.addAttribute("animal", animal);
		List<Vaccination> vaccinations = vaccinationRepository.findAllByAnimals(animal);
		model.addAttribute("vaccinations", vaccinations);
		List<AC> acs = acRepository.findAllByAnimal(animal);
		model.addAttribute("acs", acs);
		List<AnimalImage> images = imageRepository.findAllByAnimal(animal);
		model.addAttribute("images", images);

		return "profil";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showUploadForm(Model model, @RequestParam("id") int animalId) {
		model.addAttribute("animalId", animalId);
		return "uploadFile";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
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
			image.setName(file.getName());
			image.setProfile(true);
			image.setAnimal(animal);
			animal.addImage(image);
			imageRepository.save(image);

		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}

		return "forward:/profil";
	}
	@RequestMapping("/animalImage")
	public void download(@RequestParam("id") int imageId, HttpServletResponse response) {

		Optional<AnimalImage> imgOpt = imageRepository.findById(imageId);
		if (!imgOpt.isPresent())
			throw new IllegalArgumentException("No image with id " + imageId);

		AnimalImage img = imgOpt.get();

		try {
			OutputStream out = response.getOutputStream();
			// application/octet-stream
			response.setContentType(img.getContentType());
			out.write(img.getContent());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
