package com.app.springbootrestapi.service;

import java.util.Date;

import java.util.List;

import com.app.springbootrestapi.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomerList();
	
	Customer getCustomerById(int customerId);
	
	Customer createCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(int customerId);
	
	List<Customer> getCustomerByGender(String gender);

	List<Customer> getCustomerByDob(Date dateOfBirth);

	List<Customer> getCustomerByGenderWithDob(String gender, Date dob);

	List<Customer> filterOnCustomerName(String customerName);

	
	
	
	
}
