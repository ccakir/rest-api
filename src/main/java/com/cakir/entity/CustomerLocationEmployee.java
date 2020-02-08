package com.cakir.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class CustomerLocationEmployee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Employee employee;
	
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Location location;
	
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;

	
	public CustomerLocationEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerLocationEmployee(@NotNull Employee employee,
			@NotNull Location location, @NotNull Customer customer) {
		super();
		this.employee = employee;
		this.location = location;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
