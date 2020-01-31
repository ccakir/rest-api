package com.cakir.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@ApiModel(value="Customer Class")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value="Customer id")
	private long id;
	
	@ApiModelProperty(value="Customer Name")
	@NotNull
	@Column(unique= true)
	private String name;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", adresse=" + adresse
				+ ", plz=" + plz + ", ortsname=" + ortsname + ", land=" + land
				+ ", telefon=" + telefon + ", web=" + web + ", fax=" + fax
				+ ", email=" + email + "]";
	}

	@ApiModelProperty(value="Customer Adresse")
	private String adresse;

	@ApiModelProperty(value="Customer PLZ")
	private String plz;

	@ApiModelProperty(value="Customer Ort")
	private String ortsname;

	@ApiModelProperty(value="Customer Land")
	private String land;

	@ApiModelProperty(value="Customer telefon")
	private String telefon;

	@ApiModelProperty(value="Customer web")
	private String web;

	@ApiModelProperty(value="Customer fax")
	private String fax;

	@ApiModelProperty(value="Customer email")
	private String email;
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String adresse, String plz, String ortsname,
			String land, String telefon, String web, String fax, String email) {
		super();
		this.name = name;
		this.adresse = adresse;
		this.plz = plz;
		this.ortsname = ortsname;
		this.land = land;
		this.telefon = telefon;
		this.web = web;
		this.fax = fax;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrtsname() {
		return ortsname;
	}

	public void setOrtsname(String ortsname) {
		this.ortsname = ortsname;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
