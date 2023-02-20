package com.main.HospitalProjectREST.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.main.HospitalProjectREST.CustomExceptions.BranchNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.PatientNotFoundException;
import com.main.HospitalProjectREST.Repositories.BranchRepo;
import com.main.HospitalProjectREST.Repositories.EncounterRepo;
import com.main.HospitalProjectREST.Repositories.PatientRepo;
import com.main.HospitalProjectREST.entities.Branch;
import com.main.HospitalProjectREST.entities.Encounter;
import com.main.HospitalProjectREST.entities.Patient;
import com.main.HospitalProjectREST.status.Status;

@Repository
public class PatientDAO {

	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private BranchRepo branchRepo;

	@Autowired
	private EncounterRepo encounterRepo;

	// List all patients
	public List<Patient> listAllPaitents() {
		return patientRepo.findAll();
	}

	// Get patient by ID
	public Patient getById(int pid) throws PatientNotFoundException {

		Optional<Patient> tempP = patientRepo.findById(pid);
		if (tempP.isEmpty()) {
			throw new PatientNotFoundException("No Patient with ID-" + pid + " exists");
		}
		return tempP.get();
	}

	// Add patient
	public ResponseEntity<Status> addPatient(Patient patient, int bid) throws BranchNotFoundException {
		if (patient.getEncounters().isEmpty()) {
			patientRepo.save(patient);
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(new Status(200, "Patient Added successfully without encounters", now),
					HttpStatus.OK);
		} else {
			Optional<Branch> tempBranch = branchRepo.findById(bid);
			if (tempBranch.isEmpty()) {
				throw new BranchNotFoundException(
						"No branch with ID-" + bid + " exists / Enter a valid Branch ID to register the patient");
			} else {
				List<Encounter> encounters = patient.getEncounters();
				patient = patientRepo.save(patient);

				for (Encounter en : encounters) {
					en.setBranch(tempBranch.get());
					en.setPatient(patient);
					encounterRepo.save(en);

				}

			}
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(new Status(200, "Patient Added successfully with encounters", now),
					HttpStatus.OK);

		}
	}
//Update patient

	public ResponseEntity<Status> updatePatient(Patient patient) throws PatientNotFoundException {
		Optional<Patient> tempP = patientRepo.findById(patient.getPatientId());
		if (tempP.isEmpty()) {
			throw new PatientNotFoundException("No patient with ID-" + patient.getPatientId() + " exists to update");
		} else {
			tempP.get().setPatientEmail(patient.getPatientEmail());
			tempP.get().setPatientId(patient.getPatientId());
			tempP.get().setPatientName(patient.getPatientName());
			patientRepo.save(tempP.get());
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(new Status(200, "Patient details updated", now), HttpStatus.OK);
		}

	}

//Deleting patient

	public ResponseEntity<Status> deletePatientById(int pid) throws PatientNotFoundException {
		Optional<Patient> tempP = patientRepo.findById(pid);
		if (tempP.isEmpty()) {
			//return "No patient exists";
			throw new PatientNotFoundException("No patient with ID-" + pid + " exists to delete");
		} else {
			// making foreign key patient and branch as null.you cant delete otherwise

			for (Encounter e : tempP.get().getEncounters()) {
				e.setPatient(null);
				e.setBranch(null);
				// deleting all the encounters associated with the request deleting patient
				encounterRepo.delete(e);
			}

			patientRepo.delete(tempP.get());
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(new Status(200, "Patient deleted ", now), HttpStatus.OK);

		}

	}

	// Get patient By patient email

	public Patient getByEmail(String email) {
		return patientRepo.findByPatientEmail(email);
	}
}
