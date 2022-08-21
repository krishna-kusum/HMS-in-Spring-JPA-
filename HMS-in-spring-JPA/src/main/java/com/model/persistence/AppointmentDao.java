package com.model.persistence;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bean.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer>{
	
	@Query("Select a from appointments a where doctor_Id = :doctorId")
	List<Appointment> getAllAppointmentsByDoctorId(String id);
	
	@Query("Select a from appointments a where patient_Id = :doctorId")
	List<Appointment> getAllAppointmentsByPatientId(String id);

//	void appointment(String patient_id, String doc_id, Date new_date);
//	
//	String checkSlot(String slot_to_check, String d_Id, Date date);
//	
//	void storeAppointment(String p_id, String p_name, String new_slot, Date date, String d_id, String d_name, String dept);
//
//	boolean cancelAppointment(int aid);
//
//	List<Appointment> getAllAppointments(String id, int choice);
}
