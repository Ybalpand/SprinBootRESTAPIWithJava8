package com.app.springbootrestapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.app.springbootrestapi.dao.CustomerDAO;
import com.app.springbootrestapi.dao.DocumentDAO;
import com.app.springbootrestapi.entity.Customer;
import com.app.springbootrestapi.entity.Document;
import com.app.springbootrestapi.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	DocumentDAO documentDAO;

	@Override
	public List<Customer> getAllCustomerList() {
		return customerDAO.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer customer = customerDAO.findById(customerId)
				.orElseThrow(()-> new CustomerNotFoundException("Customer not found on :: "+ customerId));       //java 8 features
		return customer;
	}

	@Override
	public Customer createCustomer(Customer cust) {
		if (cust.getCustomerId() != 0) {
			Optional<Customer> newCust = customerDAO.findById(cust.getCustomerId());
			if (newCust.isPresent()) {
				return null;
			} else {
				Customer newcust = customerDAO.save(cust);
				List<Document> docList = cust.getDocument();
				
				// Used JAVA8 Features forEach 
				docList.forEach(doc -> {
					doc.setCustomer(newcust);
					documentDAO.save(doc);
				});
				System.out.println("Added New Customer"+cust.getCustomerId());
			}
			return cust;
		} else
			throw new CustomerNotFoundException("Invalid Value for Customer : " + cust.getCustomerId());
	}

	@Override
	public Customer updateCustomer(Customer cust) {
		if (cust.getCustomerId() != 0) {
			Optional<Customer> newCust = customerDAO.findById(cust.getCustomerId());
			if (newCust.isPresent()) {
				Customer newcust = customerDAO.save(cust);				
				List<Document> docList = cust.getDocument();
																		// Used JAVA8 Features forEach 
				docList.forEach(doc -> {
					doc.setCustomer(newcust);
					documentDAO.save(doc);
				});
				return cust;

			} else {
				Customer newcust = customerDAO.save(cust);
				System.out.println("Saving new customer");
				List<Document> docList = cust.getDocument();
				System.out.println("retrive doc" + docList.size());
																		// Used JAVA8 Features forEach 
				docList.forEach(doc -> {
					doc.setCustomer(newcust);
					documentDAO.save(doc);
				});
				return cust;
			}
		} else {
			throw new CustomerNotFoundException("Invalid Value for Customer : " + cust.getCustomerId());
		}
	}

	public void deleteCustomer(int customerId) {
		 customerDAO.findById(customerId)
		 .orElseThrow(()-> new CustomerNotFoundException("Can Not Delete the customer not present in Database :: "+ customerId));       //java 8 features
		documentDAO.DeleteDocumentByCustomerID(customerId);
		customerDAO.DeleteCustomerByCustomerID(customerId);

	}

	// getCustomerbyGender
	public List<Customer> getCustomerByGender(String gender) {
		List<Customer> customerList = customerDAO.CustomerByGender(gender);
		List<Customer> customerListWith = customerList.stream().distinct().collect(Collectors.toList()); // java 1.8
																											// features
		return customerListWith;
	}

	// getCustomerbyDOB
	@Override
	public List<Customer> getCustomerByDob(Date dateOfBirth) {
		List<Customer> customerList = customerDAO.CustomerByDOB(dateOfBirth);
		List<Customer> customerListWith = customerList.stream().distinct().collect(Collectors.toList());
		return customerListWith;
	}

	// getCustomerByGenderWithDOB
	@Override
	public List<Customer> getCustomerByGenderWithDob(String gender, Date dob) {
		List<Customer> customerList = customerDAO.CustomerByGenderWithDob(gender, dob);
		List<Customer> customerListWith = customerList.stream().distinct().collect(Collectors.toList());
		return customerListWith;

	}

	@Override
	public List<Customer> filterOnCustomerName(String customerName) {
		System.out.println("call the getAllCustomerList Method");

		List<Customer> customersNameList = customerDAO.findAll();
		List<Customer> getList = customersNameList.stream()
				.filter(name -> name.getCustomerName().contains(customerName)).collect(Collectors.toList());

		return getList;
	}

}
