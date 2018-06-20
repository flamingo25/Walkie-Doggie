package doggie.walkie.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import doggie.animals.model.AnimalModel;
import doggie.user.model.User;

@Entity
@Table(name = "adoption")
public class AdoptionModel implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "accepted", nullable = false)
	private boolean accepted;
	
	@Column(name = "processed", nullable = false)
	private boolean processed;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private AnimalModel animal;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private User user;
	
	@Version
	long version;
	
	public AdoptionModel() {
	}

	public AdoptionModel(Date date, boolean accepted, boolean processed) {
		super();
		this.date = date;
		this.accepted = accepted;
		this.processed = processed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public AnimalModel getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalModel animal) {
		this.animal = animal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
