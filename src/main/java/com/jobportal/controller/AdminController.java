package com.jobportal.controller;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.model.ApplicantModel;
import com.jobportal.model.Credentials;
import com.jobportal.model.RecruiterModel;
import com.jobportal.model.jobModel;
import com.jobportal.service.AdminService;
import com.jobportal.service.EmailService;
import com.jobportal.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService as;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private EmailService ems;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/AdminRegisterForm")
	public String adminRegisterForm(Model m) {
		
		m.addAttribute("recruitmodel", new RecruiterModel());
		System.out.println("in form");
		return "adminRegisterForm";
	}

	@RequestMapping("/adminregisterData")
	public String adminValid(@ModelAttribute RecruiterModel rm,Model m) {
		
		Credentials crd= us.getcredentials(rm.getEmail());
		RecruiterModel rd=as.getRecruiter(rm.getEmail());
		
		if(rd!=null  || crd!=null)
		{
			m.addAttribute("adminCheck", "already...had an Account");
			m.addAttribute("recruitmodel", new RecruiterModel());
			return "AdminRegisterForm";
		}
		else 
		{
			as.addRecruiter(rm);
		    String emailBody="""
				    Dear %s,
				    We are pleased to inform you that your administrative account has been successfully created on the Job Portal.
				    You are now authorized to manage job postings and recruitment activities through the portal, including creating, updating, and removing job listings, as well as reviewing applications and applicant resumes.
				    Please log in using your registered credentials to begin using the administrative features.
				    Should you require any assistance, our support team will be happy to help.
				    Sincerely,
				    Job Portal Administration Team
				    """.formatted(rm.getFullName());
		     ems.sendMailMesaage(rm.getEmail(),"Admin Account Activated – Job Portal Access Granted", emailBody);
		     
			 System.out.println("email for regisration successfully");
			 
			return "forward:/login";
		}
	}
	
	
	@RequestMapping("/Admin/addForm")
	public String addForm() {
		return "addForm";
	}
	
	@RequestMapping("/Admin/addJob")
	public String addjob(@ModelAttribute jobModel j) {
		
		as.addJob(j);
	 return "redirect:/Admin/getall";
	}
	
	@RequestMapping("/Admin/getall")
	public String getall(@ModelAttribute jobModel j,Model m) {
		m.addAttribute("alljobs", as.allJobs());
		return "displayAll";
	}
	
	
	@RequestMapping("/Admin/editForm")
	public String editForm(@RequestParam() int id,Model m) {
		m.addAttribute("job",as.getJob(id) );
		return "editForm";
	}
	
	@RequestMapping("/Admin/update")
	public String editForm(@ModelAttribute jobModel j) {
		
		as.updateJob(j);
		
		return "forward:/Admin/getall";
	}
	
	@RequestMapping("/Admin/delete")
	public String editForm(@RequestParam int id) {
		as.deleteJob(id);
		return "forward:/Admin/getall";
	}
	
	
	@RequestMapping("/Admin/Adminsearch")
	public String editForm(@RequestParam("searchKey") String key,Model m) 
	{
		m.addAttribute("alljobs", as.searchJobs(key));
		return "displayAll";
	}
	
	@RequestMapping("/Admin/allApplications")
	public String allApplicants(@RequestParam("id") int jobid,Model m) 
	{ 
		
		List<Integer> ids=as.allApplicantsIds(jobid);
	  
		List<ApplicantModel> aList=new ArrayList<>();
		
		if(ids.size()>0) {
			
			for(Integer i:ids)
			{
				aList.add(as.ApplicantsinfoById(i));
			}
			m.addAttribute("applicantsList", aList);
			m.addAttribute("role",as.getJob(jobid).getTitle());
			return "AllApplicants";
		}
		else 
		{
			m.addAttribute("ApplicationsCheck", "No Applications yet for this role...!");
			return "forward:/Admin/getall";
		}
		
	}
	
	
	@RequestMapping("/Admin/file")
	public ResponseEntity<byte[]> getResume(@RequestParam("id") int id){
		ApplicantModel am= us.getApplicant(id);
		String contentType = URLConnection.guessContentTypeFromName(am.getFileName());

	    if (contentType == null) 
	    {
	        contentType = "application/octet-stream"; 
	    }
		
		return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION,
	                    "inline; filename=\"" + am.getFileName() + "\"") //use inline for only view not to force download
	            .contentType(MediaType.parseMediaType(contentType))
            .body(am.getResume());
	            
		
	}
	@RequestMapping("/Admin/portal-queries")
	public String portalQueries(Model m) 
	{
	
		if(as.allFeedbacks().size()>0) {
			m.addAttribute("allFeedbacks", as.allFeedbacks());
			return "allFeedbacks";
			
		}
		else {
			m.addAttribute("FeedbackCheck", "No Portal Feedbacks....!");
			return "allFeedbacks";
		}
		
		
	}
	@RequestMapping("/Admin/FeedbackSolve")
	public String feedbackSolved(@RequestParam int id) {
		as.deleteFeedback(id);
		return "forward:/Admin/portal-queries";
	}
	
	@RequestMapping("/Admin/Adminlogout")
	public String adminlogout() {
		
		return "index";
		
	}

}
