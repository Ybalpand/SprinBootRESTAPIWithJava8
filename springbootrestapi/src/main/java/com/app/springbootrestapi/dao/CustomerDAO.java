
package com.app.springbootrestapi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootrestapi.entity.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {
	
	
	  @Modifying
	  @Transactional
	  @Query(value ="delete from customer where customer_id= :customerId" , nativeQuery = true) 
	  public void DeleteCustomerByCustomerID(int customerId);
	  
	  
	  @Modifying
	  @Transactional
	  @Query( value = "select distinct * from customer c " +
	            "join document d " +
	            "   on c.customer_id = d.customer_id " +       
	             "where c.customer_gender = :gender", nativeQuery = true)
	  public List<Customer> CustomerByGender(String gender);


	  @Modifying
	  @Transactional
	  @Query( value = "select distinct * from customer c " +
	            "join document d " +
	            "   on c.customer_id = d.customer_id " +       
	             "where c.customer_dob = :dateOfBirth", nativeQuery = true)
	public List<Customer> CustomerByDOB(Date dateOfBirth);
	  
	  
	  @Modifying
	  @Transactional
	  @Query( value = "select distinct * from customer c " +
	            "join document d " +
	            "   on c.customer_id = d.customer_id " +       
	             "where c.customer_gender = :gender AND c.customer_dob = :dob", nativeQuery = true)
	public List<Customer> CustomerByGenderWithDob(String gender, Date dob);
	  

}
