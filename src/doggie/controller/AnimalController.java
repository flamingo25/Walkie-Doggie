package doggie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import doggie.animals.dao.AnimalRepository;
import doggie.animals.dao.CompatibilityRepository;
import doggie.animals.model.AnimalModel;
import doggie.animals.model.Impfung;
import doggie.animals.model.TierArt;
import doggie.animals.model.TierVerträglichkeit;
import doggie.animals.model.Verträglichkeit;

@Controller
public class AnimalController {
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	CompatibilityRepository compatibilityRepository;
	
	@RequestMapping(value = { "/fill" })
	public String fillData(Model model) {
		
		TierArt art1 = new TierArt("Hund");
		Impfung impfung1 = new Impfung("cool");
		Impfung impfung2 = new Impfung("cool2");
		Verträglichkeit vt1 = new Verträglichkeit("vt1");
		Verträglichkeit vt2 = new Verträglichkeit("vt2");
		
		AnimalModel test = new AnimalModel("a", "b", "c", 12, "a", false, "asdasdas");
		test.addImpfung(impfung1);
		test.addImpfung(impfung2);
		test.setTierArt(art1);
		
		TierVerträglichkeit tttt = new TierVerträglichkeit();
		tttt.setTier(test);
		tttt.setVerträglichkeit(vt1);
		tttt.setStatus(true);
		
		compatibilityRepository.save(tttt);
					
		return "index";
	}
}
