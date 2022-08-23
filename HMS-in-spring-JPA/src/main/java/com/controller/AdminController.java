package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Doctor;
import com.model.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
//  Admin Functionalities Start--------------------------------------------------------------------------------------------------------------------------

	
//  Add Doctor
	@RequestMapping("/addDoctor")
	public ModelAndView addDoctorController() {
		return new ModelAndView("addDoctor");
	}
	@RequestMapping("/saveDoctor")
	public ModelAndView saveDoctorController(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Doctor doctor = new Doctor();
	
		
		doctor.setDoctorName(request.getParameter("dName"));
		doctor.setDoctorAge(Integer.parseInt(request.getParameter("dAge")));
		doctor.setDoctorGender(request.getParameter("dGender"));
		doctor.setDoctorExperience(Integer.parseInt(request.getParameter("dExperience")));
		doctor.setDoctorContact(request.getParameter("dContact"));
		doctor.setDoctorAddress(request.getParameter("dAddress"));
		doctor.setDoctorDepartment(request.getParameter("dDepartment"));
		
		String message = null;
		if (adminService.registerDoctorToDatabase(doctor))
			message = "Doctor Added Successfully";
		else
			message = "Doctor Addition Failed";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
	}
	
//  Remove Doctor

	@RequestMapping("/removeDoctorByID")
	public ModelAndView removeDoctorByIdController() {
		return new ModelAndView("DoctorIdAccepter");
	}
	@RequestMapping("/removeDoctor")
public ModelAndView removeDoctorController(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		String id = request.getParameter("dId");
		String message = null;
		if (adminService.removeDoctorFromDatabase(id))
			message = "Doctor Removed Successfully";
		else
			message = "Remove Failed";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
	}
	
//  Admin Functionalities End ------------------------------------------------------------------------------------------------------------------------
	
	

}
