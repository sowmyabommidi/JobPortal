package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.model.feedbackModel;

public interface feedbackRepo extends JpaRepository<feedbackModel,Integer> {

}
