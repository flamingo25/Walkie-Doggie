package doggie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import doggie.animals.dao.ACRepository;
import doggie.animals.dao.AnimalRepository;
import doggie.animals.dao.CompatibilityRepository;
import doggie.animals.dao.VaccinationRepository;
import doggie.animals.model.AC;
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
		AnimalModel animal = animalRepository.findById(id);
		model.addAttribute("animal", animal);
		List<Vaccination> vaccinations = vaccinationRepository.findAllByAnimals(animal);
		model.addAttribute("vaccinations", vaccinations);
		List<AC> acs = acRepository.findAllByAnimal(animal);
		model.addAttribute("acs", acs);
		
		return "profil";
	}
}
