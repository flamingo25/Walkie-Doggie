package doggie.model.animals;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

public class Tiere implements java.io.Serializable {
	
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
	private int alter;
	
	@Column(nullable = false, length = 20)
	private String geschlecht;
	
	@Column(nullable = false)
	private boolean kastriert;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String beschreibung;
	
	@Version
	long version;
	
	private Tiere() {
	}

	public Tiere(String name, String rasse, String farbe, int alter, String geschlecht, boolean kastriert,
			String beschreibung) {
		super();
		this.name = name;
		this.rasse = rasse;
		this.farbe = farbe;
		this.alter = alter;
		this.geschlecht = geschlecht;
		this.kastriert = kastriert;
		this.beschreibung = beschreibung;
	}

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

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
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
}
