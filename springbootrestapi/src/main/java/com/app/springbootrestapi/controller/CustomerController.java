package com.app.springbootrestapi.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootrestapi.constants.ConstantsUtil;
import com.app.springbootrestapi.entity.Customer;
import com.app.springbootrestapi.exception.CustomerNotFoundException;
import com.app.springbootrestapi.service.CustomerService;

@RequestMapping("/customers")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// 1 get All Customer List
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getAllCustomer() {
		System.out.println("Controller customer Layer ");
		List<Customer> listcust = customerService.getAllCustomerList();
		System.out.println("list of customer" + listcust);
		for (Customer customer : listcust) {
			System.out.println(customer);
		}
		return listcust;
	}

	// 2 Get Customer by Id
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId)
			throws CustomerNotFoundException {
		Customer customer = customerService.getCustomerById(customerId);
		if (customerId <= 0) {
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// 3 create customer
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Customer> createOrUpdateCustomer(@Valid @RequestBody Customer customer) {
		Customer newCustomercreated = customerService.createCustomer(customer);
		if (newCustomercreated == null)
			return new ResponseEntity<Customer>(newCustomercreated, new HttpHeaders(), HttpStatus.IM_USED);
		else
			return new ResponseEntity<Customer>(newCustomercreated, new HttpHeaders(), HttpStatus.CREATED);
	}

	// 4 update customer
	@PutMapping
//	@ResponseStatus(HttpStatus.OK)
	public Customer updateCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("update customer..");
		Customer updated = customerService.updateCustomer(customer);
		return updated;
	}

	// 5 delete customer
	@DeleteMapping(value = "/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteCustomer(@PathVariable("customerId") int customerId) {
		System.out.println("Start deleteCustomer....");
		customerService.deleteCustomer(customerId);
	}

	// 6 Get Customer By Gender
	@GetMapping(value = "/gender/{gender}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByGender(@PathVariable("gender") String gender) {
		System.out.println(" Get customerId with Gender...." + gender);
		return customerService.getCustomerByGender(gender);
	}

	// 7 Get Customer By DOB

	@GetMapping(value = "/dob/{dateOfBirth}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByDOB(@PathVariable("dateOfBirth") String dateOfBirth) throws ParseException {
		System.out.println(" Get customerId with dob...." + dateOfBirth.toString());
		System.out.println(" Get DOB ....");
		Date dob = ConstantsUtil.typecastToDate(dateOfBirth);
		return customerService.getCustomerByDob(dob);
	}

	// 8 GenderWithDob
	@GetMapping(value = "/{gender}/{dateOfBirth}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByGenderWithDOB(@PathVariable("gender") String gender,
			@PathVariable("dateOfBirth") String dateOfBirth) throws ParseException {
		System.out.println("Enter in Get Customer by gender and date of birth" + gender + " - " + dateOfBirth);
		Date dob = ConstantsUtil.typecastToDate(dateOfBirth);
		return customerService.getCustomerByGenderWithDob(gender, dob);

	}

	// 9 get customerList filter on customerName
	@GetMapping(value = "/filterOnCustomerName/{customerName}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> filterOnCustomerName(@PathVariable("customerName") String customerName) {
		System.out.println("Controller customer filter " + customerName);
		List<Customer> listOfcust = customerService.filterOnCustomerName(customerName);
		System.out.println("list of customer" + listOfcust);

		// java 1.8 features implement it
		/*
		 * listOfcust.forEach(customer ->{
		 * System.out.println("Customer : "+customer.toString()); });
		 */

		return listOfcust;
	}

}
