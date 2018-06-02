package doggie.animals.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "animalImage")
public class AnimalImage implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	private String name;
	private String filename;
	private boolean profile;
 
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] content;
 
	private String contentType;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private AnimalModel animal;
	
 
	@Version
	long version;
 
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
 
	public String getFilename() {
		return filename;
	}
 
	public void setFilename(String filename) {
		this.filename = filename;
	}
 
	public byte[] getContent() {
		return content;
	}
 
	public void setContent(byte[] content) {
		this.content = content;
	}
 
	public String getContentType() {
		return contentType;
	}
 
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isProfile() {
		return profile;
	}

	public void setProfile(boolean profile) {
		this.profile = profile;
	}

	public AnimalModel getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalModel animal) {
		this.animal = animal;
	}
	
	
}
