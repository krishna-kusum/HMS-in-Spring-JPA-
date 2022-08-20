package com.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Appointment;
import com.bean.Patient;
import com.bean.Schedule;
import com.model.persistence.AppointmentDao;
import com.model.persistence.DoctorDao;
import com.model.persistence.PatientDao;
import com.model.persistence.ScheduleDao;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	PatientDao patientDao;
	@Autowired
	ScheduleDao scheduleDao;
	@Autowired
	DoctorDao doctorDao;
	@Autowired
	AppointmentDao appointmentDao;
	
	

	@Override
	public boolean updatePatientProfile(String doctorId, String patientId, Map<String, String> editList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Schedule getDoctorSchedule(String doctorId) {
		return scheduleDao.findScheduleByDoctorId(doctorId);
	}

	@Override
	public boolean updateDoctorSchedule(String doctorId, Schedule schedule) {
		scheduleDao.deleteScheduleByDoctorId(doctorId);
		scheduleDao.saveSchedule(schedule);
		return true;
	}

	@Override
	public List<Schedule> getAvailableDoctors(Date date) {
		return doctorDao.getAvailableDoctors(date);
	}

	@Override
	public void displayAvailableDoctors(Date date) {
		List<Schedule> doctors = getAvailableDoctors(date);
		if (doctors == null){
			System.out.println("No Doctor Available");
		} 
		else {
			for(Schedule doc: doctors) {
				System.out.println(doc.toString());
			}
		}
	}
	
	@Override
	public List<Appointment> getMyAppointments(String id, int choice) {
		return appointmentDao.getAllAppointments(id, choice);
	}

	@Override
	public Patient getPatientProfile(String patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
