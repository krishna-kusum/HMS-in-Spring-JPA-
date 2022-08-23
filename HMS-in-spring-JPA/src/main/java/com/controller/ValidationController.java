package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Patient;
import com.model.service.PatientService;
import com.model.service.ValidateUserService;

@Controller
public class ValidationController {
	
	@Autowired
	private ValidateUserService validate;
	@Autowired
	private PatientService patientService;
	
	@RequestMapping("/validate")
	public ModelAndView validationController(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		
		String userName = request.getParameter("user");
		String password = request.getParameter("pass");

		session.setAttribute("userName", userName);
		
		
		 if(validate.isAdmin(userName, password)) {
			 if(userName.toUpperCase().charAt(0) == 'A' ) {
				 
				 modelAndView.setViewName("adminPostLogin");
			 }else if(userName.toUpperCase().charAt(0) == 'D' ) {
				 modelAndView.setViewName("doctorPostLogin");
			 }else  if(userName.toUpperCase().charAt(0) == 'P' ) {
				 modelAndView.setViewName("patientPostLogin");
			 }
		}else {
			String message = "Invalid Credentials.";
			modelAndView.addObject("message",message);
			modelAndView.setViewName("Output");
		}
		
	
		return modelAndView;
		
	}
	
	
//  Registering Patient--------------------------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/savePatient")
	public ModelAndView savePatientController(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		Patient patient = new Patient();
		patient.setPatientName(request.getParameter("pName"));
		patient.setPatientAge(Integer.parseInt(request.getParameter("pAge")));
		patient.setPatientGender(request.getParameter("pGender"));
		patient.setPatientContact(request.getParameter("pContact"));
		patient.setPatientAddress(request.getParameter("pAddress"));
		patient.setPatientSymptoms(request.getParameter("pSymptom"));
		
		String message = null;
		if (patientService.addPatient(patient))
			message = "Patient Added Successfully";
		else
			message = "Patient Addition Failed";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");

		return modelAndView;
	}

}
