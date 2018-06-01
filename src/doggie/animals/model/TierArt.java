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
@Table(name = "tierart")
public class TierArt implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;
	
	
	
	//Relationship
	
    @OneToMany(mappedBy="tierArt", fetch=FetchType.EAGER)
    @OrderBy("name")
    private Set<AnimalModel> tiere;
	
	@Version
	long version;
	
	
	
	//Constructor
	
	public TierArt() {
	}
	
	public TierArt(String tierArt) {
		super();
		this.name = tierArt;
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

	public Set<AnimalModel> getTiere() {
		return tiere;
	}

	public void setTiere(Set<AnimalModel> tiere) {
		this.tiere = tiere;
	}

	public void addTier(AnimalModel tier) {
		if (tiere == null) {
			tiere = new HashSet<AnimalModel>();
		}
		tiere.add(tier);
	}
}