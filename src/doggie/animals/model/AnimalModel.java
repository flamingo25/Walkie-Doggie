package doggie.animals.model;

import java.util.ArrayList;
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
@Table(name = "tiere")
public class AnimalModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 30)
	private String rasse;

	@Column(nullable = false, length = 30)
	private String farbe;

	@Column(nullable = false)
	private int tierAlter;

	@Column(nullable = false, length = 20)
	private String geschlecht;

	@Column(nullable = false)
	private boolean kastriert;

	@Column(nullable = false)
	private String beschreibung;
	
	
	
	//Relationship
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	TierArt tierArt;

	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Impfung> impfungen;
	
	@OneToMany(mappedBy="tier", fetch = FetchType.LAZY)
	private Set<TierVerträglichkeit> tierverträglichkeit;

	@Version
	long version;
	
	
	
	//Constructor

	public AnimalModel() {
	}

	public AnimalModel(String name, String rasse, String farbe, int alter, String geschlecht, boolean kastriert,
			String beschreibung) {
		super();
		this.name = name;
		this.rasse = rasse;
		this.farbe = farbe;
		this.tierAlter = alter;
		this.geschlecht = geschlecht;
		this.kastriert = kastriert;
		this.beschreibung = beschreibung;
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

	public String getRasse() {
		return rasse;
	}

	public void setRasse(String rasse) {
		this.rasse = rasse;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	public boolean isKastriert() {
		return kastriert;
	}

	public void setKastriert(boolean kastriert) {
		this.kastriert = kastriert;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getTierAlter() {
		return tierAlter;
	}

	public void setTierAlter(int tierAlter) {
		this.tierAlter = tierAlter;
	}

	public TierArt getTierArt() {
		return tierArt;
	}

	public void setTierArt(TierArt tierArt) {
		this.tierArt = tierArt;
	}

	public List<Impfung> getImpfungen() {
		return impfungen;
	}

	public void setImpfungen(List<Impfung> impfungen) {
		this.impfungen = impfungen;
	}
	
	public void addImpfung(Impfung impfung) {
		if (impfungen == null) {
			impfungen = new ArrayList<Impfung>();
		}
		impfungen.add(impfung);
	}

	public Set<TierVerträglichkeit> getTierverträglichkeit() {
		return tierverträglichkeit;
	}

	public void setTierverträglichkeit(Set<TierVerträglichkeit> tierverträglichkeit) {
		this.tierverträglichkeit = tierverträglichkeit;
	}
}
