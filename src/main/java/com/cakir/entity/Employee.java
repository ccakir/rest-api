package com.cakir.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String vorname;
	
	@NotNull
	private String nachname;
	
	@NotNull
	@Column( unique = true, updatable = false)
	@Size(min = 5, max = 10)
	private String username;
	
	@NotNull
	private String password;
		
	private String tel;
	
	@NotNull
	private String email;
	
	private String sprache;
	
	@NotNull
	@ManyToOne
	private Location location;
	
	

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(@NotNull String vorname, @NotNull String nachname,
			@NotNull @Size(min = 5, max = 10) String username,
			@NotNull String password, String tel, @NotNull String email,
			String sprache, @NotNull Location location) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.sprache = sprache;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSprache() {
		return sprache;
	}

	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	

}
