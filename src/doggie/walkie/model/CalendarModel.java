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
@Table(name = "calendar")
public class CalendarModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date event;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private AnimalModel animal;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private User user;
	
	@Version
	long version;
	
	public CalendarModel() {
	}

	public CalendarModel(Date event) {
		super();
		this.event = event;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEvent() {
		return event;
	}

	public void setEvent(Date event) {
		this.event = event;
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
}
