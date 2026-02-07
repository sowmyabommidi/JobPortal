package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.model.ApplicantModel;
import com.jobportal.model.JobProfile;

public interface ProfilejobRepo extends JpaRepository<JobProfile,Integer> {
	
	
	@Query("select jp from JobProfile jp where jp.jobId=:jobid and jp.profileid=:profileid")
	JobProfile getJobProfileMap(@Param("jobid") int jobid,@Param("profileid") int profileid);
	
	//ur application for job id
	@Query("select jp.jobId from JobProfile jp where  jp.profileid=:profileid")
	List<Integer> getjobProfilesIds(@Param("profileid") int profileid);
	
	
	@Query("select jp.profileid from JobProfile jp where jp.jobId=:jobid ")
	List<Integer> getAllApplicants(@Param("jobid") int jobid);
	
	
	

}
