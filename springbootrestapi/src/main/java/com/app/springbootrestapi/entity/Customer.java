package com.app.springbootrestapi.entity;

import java.io.Serializable;


import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8772842572603198736L;

	@Id
	@Column(name = "customer_id")
	// @GeneratedValue//(strategy=GenerationType.AUTO)
	private int customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_dob", nullable = false)
	@CreatedDate
	private Date dateOfBirth;

	@Column(name = "customer_gender")
	private String gender;

	@OneToMany(mappedBy = "customer" /* , orphanRemoval=true , cascade = CascadeType.REMOVE */)//, cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
	private List<Document> document ; 

	public Customer() {
		super();
	}

	public Customer(int customerId, String customerName, Date dateOfBirth, String gender, List<Document> document) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.document = document;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Document> getDocument() {
		return document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
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
		if (customerId != other.customerId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + "]";
	}



}
