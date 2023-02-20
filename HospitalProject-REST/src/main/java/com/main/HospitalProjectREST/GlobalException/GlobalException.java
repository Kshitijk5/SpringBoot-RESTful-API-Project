package com.main.HospitalProjectREST.GlobalException;



import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.main.HospitalProjectREST.CustomExceptions.BranchNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.EncounterNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.HospitalNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.PatientNotFoundException;
import com.main.HospitalProjectREST.status.Status;

@RestControllerAdvice

public class GlobalException {
	
    //Exception handling for Hospital
	@ExceptionHandler
	public ResponseEntity<Status> NosuchId(HospitalNotFoundException h) {

		Status status = new Status();
		status.setStatus(HttpStatus.NOT_FOUND.value());
		status.setMessage(h.getMessage());
		LocalDateTime now = LocalDateTime.now();
        status.setDateTime(now);
		return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);

	}
	
	  //Exception handling for Branch
	
	@ExceptionHandler
	public ResponseEntity<Status> NosuchId(BranchNotFoundException b) {

		Status status = new Status();
		status.setStatus(HttpStatus.NOT_FOUND.value());
		status.setMessage(b.getMessage());
		LocalDateTime now = LocalDateTime.now();
        status.setDateTime(now);
		return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);

	}
	
	
	//Exception handling for Encounters
	
	@ExceptionHandler
	public ResponseEntity<Status> NosuchId(EncounterNotFoundException e) {

		Status status = new Status();
		status.setStatus(HttpStatus.NOT_FOUND.value());
		status.setMessage(e.getMessage());
		LocalDateTime now = LocalDateTime.now();
        status.setDateTime(now);
		return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);

	}
	
	//Exception handling for patient
	@ExceptionHandler
	public ResponseEntity<Status> NosuchId(PatientNotFoundException p) {

		Status status = new Status();
		status.setStatus(HttpStatus.NOT_FOUND.value());
		status.setMessage(p.getMessage());
		LocalDateTime now = LocalDateTime.now();
        status.setDateTime(now);
		return new ResponseEntity<Status>(status, HttpStatus.NOT_FOUND);

	}

}
