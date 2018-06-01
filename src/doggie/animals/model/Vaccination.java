package doggie.animals.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "vaccination")
public class Vaccination implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;
	
	
	
	//Relationship
	
	@ManyToMany(mappedBy = "vaccinations",fetch=FetchType.EAGER)
	private List<AnimalModel> animals;
	
	@Version
	long version;
	
	
	//Constructor
	
	public Vaccination() {
	}
	
	public Vaccination(String name) {
		super();
		this.name = name;
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

	public List<AnimalModel> getAnimals() {
		return animals;
	}

	public void setAnimals(List<AnimalModel> animals) {
		this.animals = animals;
	}
}
