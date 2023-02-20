package com.main.HospitalProjectREST.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.HospitalProjectREST.CustomExceptions.BranchNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.PatientNotFoundException;
import com.main.HospitalProjectREST.DAO.PatientDAO;
import com.main.HospitalProjectREST.entities.Patient;
import com.main.HospitalProjectREST.status.Status;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientDAO patientDao;
	
	//List all patients
	@GetMapping("/")
	public List<Patient> listAll(){
		return patientDao.listAllPaitents();
	}
	
	
	//Get patient detail by id
	@GetMapping("/{id}")
	public Patient getById(@PathVariable("id") int pid) throws PatientNotFoundException {
		return patientDao.getById(pid);
	}
	
	//Add patient (requires BranchID for operation)
	@PostMapping("/{branchId}")
	public ResponseEntity<Status> addPatient(@RequestBody Patient patient,@PathVariable("branchId") int bid ) throws BranchNotFoundException{
		 return patientDao.addPatient(patient, bid);
	}
	
	@PutMapping("/")
	public ResponseEntity<Status> updatePatient(@RequestBody Patient patient) throws PatientNotFoundException{
		return patientDao.updatePatient(patient);
	}
	
	//Deleting a patient
	@DeleteMapping("/{id}")
	public ResponseEntity<Status> deletePatient(@PathVariable("id") int pid) throws PatientNotFoundException{
		return patientDao.deletePatientById(pid);
	}
	
	//Find by email
	@GetMapping("/patientemail/{email}")
	public Patient getByEmail(@PathVariable("email") String email) {
		return patientDao.getByEmail(email);
	}

}
