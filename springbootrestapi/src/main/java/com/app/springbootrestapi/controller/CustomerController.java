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
import org.springframework.web.bind.annotation.RequestParam;
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
		List<Customer> listcust = customerService.getAllCustomerList();
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
			return new ResponseEntity<Customer>(customer, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// 3 create customer
	@PostMapping
	public ResponseEntity<Customer> createOrUpdateCustomer(@Valid @RequestBody Customer customer) {
		Customer newCustomercreated = customerService.createCustomer(customer);
		if (newCustomercreated == null)
			return new ResponseEntity<Customer>(newCustomercreated, new HttpHeaders(), HttpStatus.IM_USED);
		else
			return new ResponseEntity<Customer>(newCustomercreated, new HttpHeaders(), HttpStatus.CREATED);
	}

	// 4 update customer
	@PutMapping
	public Customer updateCustomer(@Valid @RequestBody Customer customer) {
		Customer updated = customerService.updateCustomer(customer);
		return updated;
	}

	// 5 delete customer
	@DeleteMapping(value = "/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteCustomer(@PathVariable("customerId") int customerId) {
		customerService.deleteCustomer(customerId);
	}

	// 6 Get Customer By Gender
	@GetMapping(value = "/gender/{gender}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByGender(@PathVariable("gender") String gender) {
		return customerService.getCustomerByGender(gender);
	}

	// 7 Get Customer By DOB
	@GetMapping(value = "/dob/{dateOfBirth}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByDOB(@PathVariable("dateOfBirth") String dateOfBirth) throws ParseException {
		System.out.println(" Get customerId with dob...." + dateOfBirth.toString());
		Date dob = ConstantsUtil.typecastToDate(dateOfBirth);
		return customerService.getCustomerByDob(dob);
	}

	// 8 GenderWithDob
	
	@GetMapping(value = "/search")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByGenderWithDOB(@RequestParam(value = "gender", required = false) String gender,
	@RequestParam(value="dateOfBirth", required = false) String dateOfBirth) throws ParseException {
		Date dob = ConstantsUtil.typecastToDate(dateOfBirth);
		return customerService.getCustomerByGenderWithDob(gender, dob);
		
	}
	
	/*  used path variable 
	@GetMapping(value = "/{gender}/{dateOfBirth}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByGenderWithDOB(@PathVariable("gender") String gender,
			@PathVariable("dateOfBirth") String dateOfBirth) throws ParseException {
		Date dob = ConstantsUtil.typecastToDate(dateOfBirth);
		return customerService.getCustomerByGenderWithDob(gender, dob);

	}*/
	


	// 9 get customerList filter on customerName
	@GetMapping(value = "/filterOnCustomerName/{customerName}")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> filterOnCustomerName(@PathVariable("customerName") String customerName) {
		List<Customer> listOfcust = customerService.filterOnCustomerName(customerName);
		return listOfcust;
	}

}
