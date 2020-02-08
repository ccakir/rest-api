package com.cakir.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Nutzen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private LocalDate date;
	
	@ManyToOne
	@NotNull
	private Employee employee;
	
	@ManyToOne
	@NotNull
	private Customer customer;
	
	@ManyToOne
	@NotNull
	private Location location;
	
	@ManyToOne
	@NotNull
	private Activity activity;
	
	@Lob
	private String details;
	
	@Lob
	private String sonderleistung;
	
	@NotNull
	private double stunde;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean freigabe;

	public Nutzen() {
		super();
		
	}

	public Nutzen(@NotNull LocalDate date, @NotNull Employee employee,
			@NotNull Customer customer, @NotNull Location location,
			@NotNull Activity activity, String details, String sonderleistung,
			@NotNull double stunde, boolean freigabe) {
		super();
		this.date = date;
		this.employee = employee;
		this.customer = customer;
		this.location = location;
		this.activity = activity;
		this.details = details;
		this.sonderleistung = sonderleistung;
		this.stunde = stunde;
		this.freigabe = freigabe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSonderleistung() {
		return sonderleistung;
	}

	public void setSonderleistung(String sonderleistung) {
		this.sonderleistung = sonderleistung;
	}

	public double getStunde() {
		return stunde;
	}

	public void setStunde(double stunde) {
		this.stunde = stunde;
	}

	public boolean isFreigabe() {
		return freigabe;
	}

	public void setFreigabe(boolean freigabe) {
		this.freigabe = freigabe;
	}
	
	
	
}
