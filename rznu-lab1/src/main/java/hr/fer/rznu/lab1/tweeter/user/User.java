package hr.fer.rznu.lab1.tweeter.user;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	
	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public User(int id, String firstName, String lastName, LocalDate dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	
	public User(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}
	
}
