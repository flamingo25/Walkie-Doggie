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
@Table(name = "impfung")
public class Impfung implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;
	
	
	
	//Relationship
	
	@ManyToMany(mappedBy = "impfungen",fetch=FetchType.EAGER)
	private List<AnimalModel> tiere;
	
	@Version
	long version;
	
	
	//Constructor
	
	public Impfung() {
	}

	public Impfung(String beschreibung) {
		super();
		this.name = beschreibung;
	}
	
	
	
	//Getter + Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeschreibung() {
		return name;
	}

	public void setBeschreibung(String beschreibung) {
		this.name = beschreibung;
	}

	public List<AnimalModel> getTiere() {
		return tiere;
	}

	public void setTiere(List<AnimalModel> tiere) {
		this.tiere = tiere;
	}
}