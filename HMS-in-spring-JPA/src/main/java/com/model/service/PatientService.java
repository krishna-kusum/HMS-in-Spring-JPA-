package com.model.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bean.Appointment;
import com.bean.Patient;
@Service
public interface PatientService {
	
	List<Patient> getAllPatient();
	
	boolean addPatient(Patient patient);
	
	boolean deletePatient(String patientId);

	Patient getPatientById(String patientId);

	String getLastPatientId();
	
	String setNewPatientId();

	List<Appointment> getMyAppointments(String pid);

	boolean rescheduleAppointment(int aid, Date newDate);
//
//	void requestAppointment(String id, String doc_id, Date date);
//
	boolean cancelAppointmentRequest(int aid);
//
}
