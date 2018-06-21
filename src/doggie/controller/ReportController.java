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
import doggie.animals.dao.CompatibilityRepository;
import doggie.animals.dao.VaccinationRepository;
import doggie.animals.model.AnimalModel;
import doggie.animals.model.Compatibility;
import doggie.animals.model.Vaccination;
import doggie.user.dao.UserDao;
import doggie.user.model.User;
import doggie.walkie.dao.AdoptionRepository;
import doggie.walkie.model.AdoptionModel;

@Controller
public class ReportController {
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	CompatibilityRepository compatibilityRepository;

	@Autowired
	VaccinationRepository vaccinationRepository;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AdoptionRepository adoptionRepository;
	
	@RequestMapping(value = { "/adopt/report" })
	public String report(Model model, @RequestParam int id) {
		
		Optional<AdoptionModel> adoptionOpt = adoptionRepository.findById(id);
		
		if (!adoptionOpt.isPresent())
			throw new IllegalArgumentException("No adoption with id " + id);

		AdoptionModel adoption = adoptionOpt.get();
		
		if (adoption.isAccepted() && adoption.isProcessed()) {
		
		User user = userDao.findById(adoption.getUser().getId()).get();
		
		AnimalModel animal = animalRepository.findById(adoption.getAnimal().getId()).get();
		
		List<Vaccination> vaccinations = vaccinationRepository.findAllByAnimals(animal);
		List<Compatibility> compatibilities = compatibilityRepository.findAllByAnimals(animal);
		
		model.addAttribute("animal", animal);
		model.addAttribute("vaccinations", vaccinations);
		model.addAttribute("compatibilities", compatibilities);
		model.addAttribute("user", user);
		
		return "pdfReport";
		} else
			return "forward:/adopt/adoption";
	}
}
