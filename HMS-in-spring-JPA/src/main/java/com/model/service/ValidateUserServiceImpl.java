package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Doctor;
import com.bean.Login;
import com.bean.Patient;
import com.model.persistence.DoctorDao;
import com.model.persistence.DoctorDaoImpl;
import com.model.persistence.LoginDao;
import com.model.persistence.LoginDaoImpl;
import com.model.persistence.PatientDao;
import com.model.persistence.PatientDaoImpl;

@Service
public class ValidateUserServiceImpl implements ValidateUserService {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private DoctorDao doctorDao ;
	
	
	

	
	
//	@Override
//	public boolean registerPatient(Patient patient) {
//		return patientDao.save(patient);
//	}

//	@Override
//	public boolean registerDoctor(Doctor doctor) {
//		return doctorDao.addDoctor(doctor);
//	}

	@Override
	public boolean isPatient(String id, String Password) {
		
//		String password =  loginDao.findPasswordById(id);
		Login login =  loginDao.findPasswordById(id);
		if(login == null)
			return false;
		String password = login.getPassword();
		if(Password.equals(password))
			return true;
		return false;
			
	}

	@Override
	public boolean isDoctor(String id, String Password) {
//		return loginDao.findPasswordById(id);
//		String password =  loginDao.findPasswordById(id);
		Login login =  loginDao.findPasswordById(id);
		if(login == null)
			return false;
		String password = login.getPassword();
		if(Password.equals(password))
			return true;
		return false;
	}

	@Override
	public boolean isAdmin(String id, String Password) {
//		return loginDao.findPasswordById(id);
		Login login =  loginDao.findPasswordById(id);
		if(login == null)
			return false;
		String password = login.getPassword();
		if(Password.equals(password))
			return true;
		return false;
	}

//	@Override
//	public boolean registerUser(String id, String Password) {
//		return loginDao.registerUser(id, Password,2);
//	}
}
