package com.jobportal.controller;

import java.io.IOException;
import java.net.URLConnection;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.model.ApplicantModel;
import com.jobportal.model.Credentials;
import com.jobportal.model.JobProfile;
import com.jobportal.model.applicant;
import com.jobportal.model.feedbackModel;
import com.jobportal.model.jobModel;
import com.jobportal.service.EmailService;
import com.jobportal.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private EmailService ems;
	
	@Autowired
	private UserService us;
	

	
	@RequestMapping("/registerForm")
	  public String register1()  {
	
		return "register1";
	   }
  @RequestMapping("/register1Data")
  public String register1(@ModelAttribute ApplicantModel am,HttpSession hs,Model m) throws IOException {
	 ApplicantModel check=us.getApplicant(am.getEmail());
	
	 Credentials crd= us.getcredentials(am.getEmail());
	
		 if((check!=null) || (crd!=null)) {
			
			 m.addAttribute("msg","Account already exist!...");
			 return "register1";
		 }
		 else {
		
	        hs.setAttribute("data",am);

	return "register2";}
   }
  @RequestMapping("/register2Data")
  public String register2(@ModelAttribute applicant am,HttpSession hs) throws IOException {
	 ApplicantModel da=(ApplicantModel)hs.getAttribute("data");
	 da.setResume(am.getResume().getBytes());
	 da.setFileName(am.getResume().getOriginalFilename());
	 Credentials cr=new Credentials();
	 cr.setRole("User");
	 cr.setEmail(da.getEmail());
	 cr.setPassword(da.getPassword());
    us.addCredentials(cr);
	 us.addApplicant(da);
		String emailBody="""
				Dear %s,
	Congratulations! Your registration on Job Portal has been completed successfully.
	You can now log in and start exploring job opportunities that match your skills and interests.
	If you have any questions or need assistance, feel free to contact our support team.
	We wish you great success in your job search!
	Best regards,
	@Job Portal Team   """	.formatted(da.getFullName());
	 ems.sendMailMesaage(da.getEmail(),"🎉Registration Successfully Completed – Welcome to Job Portal", emailBody);
		System.out.println("email for regisration successfully");
	return "login";
   }
  
  @RequestMapping("/login")
  public String loginForm() {
	return "login";
   }
  
  

  @RequestMapping("/User/loginSuccess")
  public String loginSuccess(Principal pr,Model m) {
	  m.addAttribute("name",us.getApplicant(pr.getName()).getFullName());
		m.addAttribute("id",us.getApplicant(pr.getName()).getId());
	return "loginSuccess";
   }
  @RequestMapping("/User/profile/{id}")
  public String profile(@PathVariable("id") int id,Model m) {
		m.addAttribute("user", us.getApplicant(id));
	return "profile";
   }

  @RequestMapping("/User/AllJobs")
  public String Alljobs(@RequestParam("id") int id,@RequestParam("name") String name,Model m) {
		m.addAttribute("name",name);
		m.addAttribute("id",id);
	  m.addAttribute("alljobs", us.allJobs());
	return "alljobs";
   }
  @RequestMapping("/User/home")
  public String home(@RequestParam("id") int id,@RequestParam("name") String name,Model m) {
	  m.addAttribute("name",name);
		m.addAttribute("id",id);
	return "loginSuccess";
   }
  @RequestMapping("/User/apply")
  public String home(@RequestParam("jobid") int jobid,@RequestParam("profileid") int profileid,Model m) {
	  JobProfile jb=us.getProfileJobMap(jobid, profileid);
	  jobModel jm=us.getJob(jobid);
	  ApplicantModel am=us.getApplicant(profileid);
	  if(jb!=null) {
		String name=  am.getFullName();
		 m.addAttribute("msg", "Already...Applied");
		  m.addAttribute("postid",jobid);
		  return "forward:/User/AllJobs?name="+name+"&id="+profileid;
	  }
	  else {
	 JobProfile jp= new JobProfile();
	 jp.setId(0);
	 jp.setJobId(jobid);
	 jp.setProfileid(profileid);
	 us.profileJobMap(jp);
	 //--------------------sending email
	 String emailBody="""
			Dear %s,
			Thank you for applying for the position of %s at %s through our Job Portal.We confirm that we have received your application.
			
			Our recruitment team will review your profile, and if it matches the requirements of the role, you will be contacted for further  Hiring process through the email address or contact number provided in your application.
			
			We appreciate your interest in this opportunity and encourage you to continue exploring other relevant roles available on our platform.
			
			Wishing you every success in your career journey.
			
			Warm regards,
			Job Portal Team """.formatted(am.getFullName(),jm.getTitle(),jm.getCompany());
	  ems.sendMailMesaage(am.getEmail(),"Application Received – "+ jm.getTitle()+" at "+jm.getCompany(), emailBody);
	  return "appliedSuccess";
	  }
   }
  @RequestMapping("/User/yourApplications")
  public String yourApplications(@RequestParam("id") int id,@RequestParam("name") String name,Model m) {
	
	 List<Integer> li= us.getJobIds(id);
	 List<jobModel> jobs=new ArrayList<>();
	 for(Integer jobId:li) {
		jobs.add(us.getJob(jobId)) ;
		
	 }
	 m.addAttribute("yourApplications",jobs);
	 m.addAttribute("name", name);
	 m.addAttribute("id", id);

	
	return "urApplications";
   }
  @RequestMapping("/User/withdraw")
  public String withdraw(@RequestParam("jobid") int jobid,@RequestParam("profileid") int profileid,Model m) {
	 us.deleteApplication(jobid,profileid);
	 ApplicantModel am=us.getApplicant(profileid);
	return "forward:/User/yourApplications?name="+am.getFullName()+"&id="+profileid;
   }
  
  @RequestMapping("/User/viewPost")
  public String viewPost(@RequestParam("jobid") int jobid,@RequestParam("profileid") int profileid,Model m) {
	 ApplicantModel am=us.getApplicant(profileid);
	 m.addAttribute("post", us.getJob(jobid));
	 m.addAttribute("name",am.getFullName() );
	
	 m.addAttribute("id",profileid);
	return "dedicatedPost";
   }
	@RequestMapping("/User/CandidateSearch")
	public String search(@RequestParam("searchKey") String key,@RequestParam("id") int id,@RequestParam("name") String name,Model m) 
	{
		System.out.println("i am in");
		m.addAttribute("alljobs", us.searchCandidateJobs(key));
		m.addAttribute("name",name);
		m.addAttribute("id",id);
		System.out.println(us.searchCandidateJobs(key));
		return "alljobs";
	}
	
	@RequestMapping("/User/file")
	public ResponseEntity<byte[]> getResume(@RequestParam("id") int id){
		ApplicantModel am= us.getApplicant(id);
		String contentType = URLConnection.guessContentTypeFromName(am.getFileName());

	    if (contentType == null) {
	        contentType = "application/octet-stream"; 
	    }
		return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION,
	                    "inline; filename=\"" + am.getFileName() + "\"")
	            .contentType(MediaType.parseMediaType(contentType))
            .body(am.getResume());
	            
	}
	@RequestMapping("/User/profileUpdate")
  public String profileUpdate(@ModelAttribute ApplicantModel am,@RequestParam("profileid") int id,Model m) {
		ApplicantModel Am=us.getApplicant(id);
		Am.setFullName(am.getFullName());
		Am.setProfileSummary(am.getProfileSummary());
		Am.setCity(am.getCity());
		Am.setContactNumber(am.getContactNumber());
		Am.setDateOfBirth(am.getDateOfBirth());
		Am.setGender(am.getGender());
		Am.setGName(am.getGName());
		Am.setGBoard(am.getGBoard());
		Am.setGScore(am.getGScore());
		Am.setGPassingYear(am.getGPassingYear());Am.setHName(am.getHName());
		Am.setHBoard(am.getHBoard());
		Am.setHScore(am.getHScore());
		Am.setHPassingYear(am.getHPassingYear());Am.setSName(am.getSName());
		Am.setSBoard(am.getSBoard());
		Am.setSScore(am.getSScore());
		Am.setSPassingYear(am.getSPassingYear());
		Am.setSkills(am.getSkills());
		Am.setInternship(am.getInternship());
		Am.setLanguage(am.getLanguage());
		Am.setProjects(am.getProjects());
		Am.setAchievements(am.getAchievements());
		Am.setExperience(am.getExperience());
		us.updateApplicant(Am);
		m.addAttribute("id",Am.getId());
		m.addAttribute("name",Am.getFullName());
		
		return "loginSuccess";
	  
  }
	@RequestMapping("/User/editResume")
	public String editResume(@RequestParam("resume") MultipartFile file,@RequestParam("profileid") int id,Model m) throws IOException {
		ApplicantModel Am=us.getApplicant(id);
		Am.setResume(file.getBytes());
		Am.setFileName(file.getOriginalFilename());
		us.updateApplicant(Am);
		m.addAttribute("id",Am.getId());
		m.addAttribute("name",Am.getFullName());
		System.out.println("resumeUpdated");
		return "loginSuccess";
		
	}
	
	@RequestMapping("/contactus")
	public String contactus() {
		return "contactus";
	}
	@RequestMapping("/User/contactus")
	public String ucontactus() {
		return "contactus";
	}
	@RequestMapping("/User/homeSearch")
	public String homeSearch(@RequestParam("jobType") String jobType,@RequestParam("jobLocation") String jobLocation,Model m,@RequestParam("id") int id) {
		List<jobModel> li=us.allJobsByLocationType(jobType, jobLocation);
		ApplicantModel am=us.getApplicant(id);
			m.addAttribute("listEmpty","No Results Found");
			m.addAttribute("alljobs",li);
			m.addAttribute("name",am.getFullName());
			m.addAttribute("id",id);
			return "alljobs";
		}
		


@RequestMapping("/User/feedbackData")
public String feedback(@ModelAttribute feedbackModel f,Model m) {
	us.addfeedback(f);
	m.addAttribute("feedbackeResponse", "Feedback Received...our team will Rectify Your Problem soon...");
	return "contactus";
}
}
