package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobportal.model.ApplicantModel;

@Repository
public interface candidateRepo extends JpaRepository<ApplicantModel,Integer> {
	
	@Query("select a from ApplicantModel a where email=:email and password=:password")
    ApplicantModel checkApplicant(@Param("email") String email,@Param("password") String password);
	
	@Query("select a from ApplicantModel a where email=:email")
    ApplicantModel getByEmail(@Param("email") String email);
	
   
	

}
