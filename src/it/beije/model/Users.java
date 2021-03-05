package it.beije.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
private Integer id; 
	@Column(name="email")
private String email;
	@Column(name="first_name")
private String firstNname;
	@Column(name="second_name")
private String secondName;
	@Column(name="pasword")
private String password;
	
public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getFirstNname() {
	return firstNname;
}
public void setFirstNname(String firstNname) {
	this.firstNname = firstNname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

	
}
