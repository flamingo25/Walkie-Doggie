package doggie.model.animals;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

public class TierArt implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String tierArt;
	
	@Version
	long version;
	
	public TierArt() {
	}

	public TierArt(String tierArt) {
		super();
		this.tierArt = tierArt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTierArt() {
		return tierArt;
	}

	public void setTierArt(String tierArt) {
		this.tierArt = tierArt;
	}
}
