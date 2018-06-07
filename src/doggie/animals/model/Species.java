package doggie.animals.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "species")
public class Species implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30, unique = true)
	private String name;
	
	
	
	//Relationship
	
    @OneToMany(mappedBy="species", fetch=FetchType.EAGER)
    private Set<AnimalModel> animals;
	
	@Version
	long version;
	
	
	
	//Constructor
	
	public Species() {
	}
	
	public Species(String name) {
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

	public Set<AnimalModel> getAnimals() {
		return animals;
	}

	public void setAnimals(Set<AnimalModel> animals) {
		this.animals = animals;
	}
	
	public void addAnimal(AnimalModel animal) {
		if (animals == null) {
			animals = new HashSet<AnimalModel>();
		}
		animals.add(animal);
	}
}
