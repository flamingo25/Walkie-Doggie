package doggie.animals.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "animals")
public class AnimalModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 30)
	private String breed;

	@Column(nullable = false, length = 30)
	private String color;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false, length = 20)
	private String gender;

	@Column(nullable = false)
	private boolean castrated;
	
	@Column(nullable = false)
	private boolean chipped;

	@Column(nullable = false)
	private String description;
	
	
	
	//Relationship
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Species species;

	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Vaccination> vaccinations;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Compatibility> compatibilities;
	
	@OneToMany(mappedBy="animal", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<AnimalImage> image;

	@Version
	long version;
	
	
	
	//Constructor

	public AnimalModel() {
	}	
	
	public AnimalModel(String name, String breed, String color, int age, String gender, boolean castrated,
			boolean chipped, String description) {
		super();
		this.name = name;
		this.breed = breed;
		this.color = color;
		this.age = age;
		this.gender = gender;
		this.castrated = castrated;
		this.chipped = chipped;
		this.description = description;
	}
	
	

	//Getter + Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isCastrated() {
		return castrated;
	}

	public void setCastrated(boolean castrated) {
		this.castrated = castrated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public List<Vaccination> getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(List<Vaccination> vaccinations) {
		this.vaccinations = vaccinations;
	}
	
	public void addVaccination(Vaccination vaccination) {
		if (vaccinations == null) {
			vaccinations = new ArrayList<Vaccination>();
		}
		vaccinations.add(vaccination);
	}
	
	public boolean isChipped() {
		return chipped;
	}

	public void setChipped(boolean chipped) {
		this.chipped = chipped;
	}

	public List<Compatibility> getCompatibilities() {
		return compatibilities;
	}

	public void setCompatibilities(List<Compatibility> compatibilitys) {
		this.compatibilities = compatibilitys;
	}
	
	public void addCompatibility(Compatibility compatibility) {
		if (compatibilities == null) {
			compatibilities = new ArrayList<Compatibility>();
		}
		compatibilities.add(compatibility);
	}

	public Set<AnimalImage> getImage() {
		return image;
	}

	public void setImage(Set<AnimalImage> image) {
		this.image = image;
	}
	
	public void addImage(AnimalImage newImage) {
		if (image == null) {
			image = new HashSet<AnimalImage>();
		}
		image.add(newImage);
	}
}
