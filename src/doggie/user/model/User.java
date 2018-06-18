package doggie.user.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import doggie.animals.model.AnimalModel;
import doggie.walkie.model.AdoptionModel;
import doggie.walkie.model.CalendarModel;

@Entity
@Table(name = "users")
public class User implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String userName;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	
	
	//Relationship
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<UserRole> userRoles;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private UserProfile userProfile;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "favourites", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "animal_id") })
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<AnimalModel> favourites;
	
	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<CalendarModel> events;
	
	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<AdoptionModel> adoptions;
	
	
	//Constructor

	public User() {
	}

	public User(String userName, String password, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void addUserRole(UserRole userRole) {
		if (userRoles == null)
			userRoles = new HashSet<UserRole>();
		userRoles.add(userRole);
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<AnimalModel> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<AnimalModel> favourites) {
		this.favourites = favourites;
	}

	public void addFavourite(AnimalModel animal) {
		if (favourites == null) {
			favourites = new ArrayList<AnimalModel>();
		}
		favourites.add(animal);
	}

	public Set<CalendarModel> getEvents() {
		return events;
	}

	public void setEvents(Set<CalendarModel> events) {
		this.events = events;
	}
	
	public void addEvent(CalendarModel event) {
		if (events == null) {
			events = new HashSet<CalendarModel>();
		}
		events.add(event);
	}

	public Set<AdoptionModel> getAdoptions() {
		return adoptions;
	}

	public void setAdoptions(Set<AdoptionModel> adoptions) {
		this.adoptions = adoptions;
	}
	
	public void addAdoption(AdoptionModel adoption) {
		if (adoptions == null) {
			adoptions = new HashSet<AdoptionModel>();
		}
		adoptions.add(adoption);
	}
	
}