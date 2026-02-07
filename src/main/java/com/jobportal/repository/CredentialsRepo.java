package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobportal.model.Credentials;

@Repository
public interface CredentialsRepo  extends JpaRepository<Credentials,Integer>{
	
	@Query("select cr from Credentials cr where cr.email=:email")
	Credentials getcredentails(@Param("email") String email);
	

}
