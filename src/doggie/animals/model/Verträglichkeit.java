package doggie.animals.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "vertraeglichkeit")
public class Verträglichkeit implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String beschreibung;
	
	
	
	//Relationship
	
	@OneToMany(mappedBy="verträglichkeit", fetch = FetchType.LAZY)
	private Set<TierVerträglichkeit> tierverträglichkeit;
	
	@Version
	long version;
	
	
	
	//Constructor
	
	public Verträglichkeit() {
	}

	public Verträglichkeit(String beschreibung) {
		super();
		this.beschreibung = beschreibung;
	}
	
	
	
	//Getter + Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Set<TierVerträglichkeit> getTiere() {
		return tierverträglichkeit;
	}

	public void setTiere(Set<TierVerträglichkeit> tierverträglichkeit) {
		this.tierverträglichkeit = tierverträglichkeit;
	}
}
