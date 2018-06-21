package doggie.user.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "userprofile")
public class UserProfile implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "firstname", nullable = false, length = 45)
	private String firstName;
	
	@Column(name = "lastname", nullable = false, length = 45)
	private String surName;
	
	@Temporal(TemporalType.DATE)
	private Date dayOfBirth;
	
	@Column(name = "email", nullable = false, length = 45)
	private String email;
	
	@Column(name = "zip", nullable = false)
	private int zip;
	
	@Column(name = "city", nullable = false, length = 30)
	private String city;
	
	@Column(name = "address", nullable = false, length = 45)
	private String address;
	
	@Column(name = "phone", nullable = false, length = 30)
	private String phone;
	
	@Version
	long version;
	
	@OneToOne(mappedBy = "userProfile")
	private User user;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private UserImage image;
	
	
	
	public UserProfile() {
	}

	public UserProfile(String firstName, String surName, Date dayOfBirth, String email, int zip, String city,
			String address, String phone) {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.dayOfBirth = dayOfBirth;
		this.email = email;
		this.zip = zip;
		this.city = city;
		this.address = address;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserImage getImage() {
		return image;
	}

	public void setImage(UserImage image) {
		this.image = image;
	}
}
