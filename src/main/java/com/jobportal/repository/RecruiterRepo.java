package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobportal.model.RecruiterModel;

@Repository
public interface RecruiterRepo extends JpaRepository<RecruiterModel,Integer> {
	
	@Query("select rm from RecruiterModel rm where rm.email=:email")
	RecruiterModel getrecruiter(@Param("email") String email);

}
