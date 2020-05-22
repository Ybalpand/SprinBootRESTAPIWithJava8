package com.app.springbootrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootrestapi.entity.Document;

@Repository
public interface DocumentDAO extends JpaRepository<Document, Integer> {

	  @Modifying
	  @Transactional
	  @Query(value ="delete from document where customer_id= :customerId",  nativeQuery = true) 
	  public void DeleteDocumentByCustomerID(int customerId );

}
