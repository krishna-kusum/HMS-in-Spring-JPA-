package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bean.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, String>{

	@Query("SELECT p FROM patient p ORDER BY p.patient_id DESC LIMIT 1")
	String getLastPatientId();

//	List<Patient> getPatientList();
//	
//	boolean addPatient(Patient patient);
//
//	boolean removePatient(String patientId);
//
//	Patient getPatientById(String patientId);
//
//	int getLastPId();
		
}