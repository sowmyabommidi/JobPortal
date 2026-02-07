package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobportal.model.jobModel;

@Repository
public interface PortalRepository  extends JpaRepository<jobModel,Integer>{
	
	@Query("select j from jobModel j where (j.Company like concat('%',:key,'%')) or"
			+ " (j.Code like concat('%',:key,'%')) or (j.Title like concat('%',:key,'%')) or  "
			+ "(j.Description like concat('%',:key,'%')) or (j.Requirements like concat('%',:key,'%')) or"
			+ " (j.EmployementType like concat('%',:key,'%')) or (j.ExperienceLevel like concat('%',:key,'%')) or "
			+ "(j.Package like concat('%',:key,'%')) or  (j.workType like concat('%',:key,'%')) or  (j.Location like concat('%',:key,'%'))")
	List<jobModel> searchResults(@Param("key")String key);
	
	
	
	@Query("select j from jobModel j where (j.Title like concat('%',:jobType,'%')) or  "
			+ "(j.Location like concat('%',:jobLocation,'%'))")
	List<jobModel> searchResultsByLocationtype(@Param("jobType")String jobType,@Param("jobLocation")String jobLocation);
	
	

}
