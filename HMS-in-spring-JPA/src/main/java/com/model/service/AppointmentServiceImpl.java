package com.model.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bean.Appointment;
import com.bean.PrevSlots;
import com.bean.ProcedureAppointment;
import com.model.persistence.AppointmentDao;
import com.model.persistence.AppointmentDaoImpl;
import com.model.persistence.ProcedureAppointmentDao;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentDao appointmentDao;
	@Autowired
	private ProcedureAppointmentDao procedureAppointmentDao;

	@Override
	public Appointment requestAppointment(String patientId, String doctorId, Date date) {
		appointmentDao.callProcedure(patientId, doctorId);
		ProcedureAppointment pA = procedureAppointmentDao.findTopByOrderByProcedureIdDesc();
		
		
		
		Time slot = pA.getStartSlot();
		LocalTime endSlot = LocalTime.parse(pA.getEndSlot().toString());
		
//		Date date = date2;
		List<Appointment> lastAppointments = appointmentDao.findAppointmentByDoctorIdAndDate(doctorId, date);
		Appointment lastAppointment;
		if(lastAppointments.isEmpty()) {
			lastAppointment = null;
			
		}else {
		lastAppointment = lastAppointments.get(lastAppointments.size()-1);
		}
		LocalTime newSlot;
		if(lastAppointment!=null) {
			Time lastSlot = lastAppointment.getSlot();
			newSlot = LocalTime.parse(lastSlot.toString());
			newSlot = newSlot.plusMinutes(20);
		}else {
			newSlot = LocalTime.parse(slot.toString());
		}
		
		
		

		int a = newSlot.compareTo(endSlot);
		
		Appointment appointment = new Appointment();
		
		if(a != 0) {
			appointment.setDate(date);
			appointment.setDepartment(pA.getDept());
			appointment.setDoctorId(doctorId);
			appointment.setPatientId(patientId);
			appointment.setDoctorName(pA.getDName());
			appointment.setPatientName(pA.getPName());
			appointment.setSlot(Time.valueOf(newSlot));
			
			appointmentDao.save(appointment);
			
			return appointmentDao.findById(appointmentDao.findTopByOrderByAppointmentIdDesc().getAppointmentId()).get();
			
		}	
		return appointment;
	}
	
	
	
	
//	@Override
//	public List<PrevSlots> prevSlots(String dId) {
//		// TODO Auto-generated method stub
//		return appointmentDaoImpl.prevAppointments(dId);
//	}

}
