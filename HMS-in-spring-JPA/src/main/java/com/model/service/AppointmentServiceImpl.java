package com.model.service;

import java.sql.Date;
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
	public Appointment requestAppointment(String id, String doc_id, Date date) {
		// TODO Auto-generated method stub
		appointmentDao.callProcedure(id, doc_id);
		ProcedureAppointment pA = procedureAppointmentDao.findTopByOrderByProcedureIdDesc();
		return null;
	}
	
	
	
	
//	@Override
//	public List<PrevSlots> prevSlots(String dId) {
//		// TODO Auto-generated method stub
//		return appointmentDaoImpl.prevAppointments(dId);
//	}

}
