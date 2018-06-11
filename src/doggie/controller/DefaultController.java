package doggie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import doggie.animals.dao.AnimalRepository;
import doggie.animals.dao.CompatibilityRepository;
import doggie.animals.dao.ImageRepository;
import doggie.animals.dao.SpeciesRepository;
import doggie.animals.dao.VaccinationRepository;
import doggie.animals.model.AnimalModel;
import doggie.animals.model.Compatibility;
import doggie.animals.model.Species;
import doggie.animals.model.Vaccination;

@Controller
public class DefaultController {
	
	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	CompatibilityRepository compatibilityRepository;

	@Autowired
	VaccinationRepository vaccinationRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;

	@Autowired
	ImageRepository imageRepository;
	
	@RequestMapping(value = { "/", "list" })
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}
	
	@RequestMapping(value = { "/fill" })
	public String fillData(Model model) {

		Species art1 = new Species("Hund");
		speciesRepository.save(art1);
		Species art2 = new Species("Katze");
		speciesRepository.save(art2);
		Species art3 = new Species("Kleintier");
		speciesRepository.save(art3);
		
		
		Vaccination impfung1 = new Vaccination("impfung1");
		Vaccination impfung2 = new Vaccination("impfung2");
		Vaccination impfung3 = new Vaccination("impfung3");
		Vaccination impfung4 = new Vaccination("impfung4");
		Vaccination impfung5 = new Vaccination("impfung5");
		vaccinationRepository.save(impfung1);
		vaccinationRepository.save(impfung2);
		vaccinationRepository.save(impfung3);
		vaccinationRepository.save(impfung4);
		vaccinationRepository.save(impfung5);
		
		
		Compatibility vt1 = new Compatibility("vt1");
		Compatibility vt2 = new Compatibility("vt2");
		Compatibility vt3 = new Compatibility("vt3");
		Compatibility vt4 = new Compatibility("vt4");
		Compatibility vt5 = new Compatibility("vt5");
		compatibilityRepository.save(vt1);
		compatibilityRepository.save(vt2);
		compatibilityRepository.save(vt3);
		compatibilityRepository.save(vt4);
		compatibilityRepository.save(vt5);

		
		AnimalModel test = new AnimalModel("a", "b", "c", 12, "a", false, true, "asdasdas");
		test.addVaccination(vaccinationRepository.findById(2).get());
		test.addVaccination(vaccinationRepository.findById(4).get());
		test.addCompatibility(compatibilityRepository.findById(1).get());
		test.addCompatibility(compatibilityRepository.findById(2).get());
		test.addCompatibility(compatibilityRepository.findById(5).get());		
		test.setSpecies(speciesRepository.findById(1).get());

		animalRepository.save(test);

		return "index";
	}
}
