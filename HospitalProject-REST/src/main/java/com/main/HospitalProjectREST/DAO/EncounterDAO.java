package com.main.HospitalProjectREST.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.main.HospitalProjectREST.CustomExceptions.BranchNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.EncounterNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.PatientNotFoundException;
import com.main.HospitalProjectREST.Repositories.BranchRepo;
import com.main.HospitalProjectREST.Repositories.EncounterRepo;
import com.main.HospitalProjectREST.Repositories.PatientRepo;
import com.main.HospitalProjectREST.entities.Branch;
import com.main.HospitalProjectREST.entities.Encounter;
import com.main.HospitalProjectREST.entities.Patient;
import com.main.HospitalProjectREST.status.Status;

@Repository
public class EncounterDAO {

	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private BranchRepo branchRepo;

	@Autowired
	private EncounterRepo encounterRepo;

	// List all encounters

	public List<Encounter> listAllEncounters() {
		return encounterRepo.findAll();
	}

	// Get encounter by Id
	public Encounter getEncounterById(int eid) throws EncounterNotFoundException {
		Optional<Encounter> etemp = encounterRepo.findById(eid);
		if (etemp.isEmpty()) {
			throw new EncounterNotFoundException("No encounter with ID-" + eid + " exists");
		}
		return etemp.get();
	}

	// Add encounter for an existing patient
	public ResponseEntity<Status> addEncounter(int pid, int bid, Encounter encounter)
			throws PatientNotFoundException, BranchNotFoundException {
		Optional<Patient> tempP = patientRepo.findById(pid);
		Optional<Branch> tempB = branchRepo.findById(bid);
		if (tempP.isEmpty()) {
			throw new PatientNotFoundException("No Patient with ID-" + pid + " exists");
		} else if (tempB.isEmpty()) {
			throw new BranchNotFoundException("No branch with ID-" + bid + " exists");
		} else {
			encounter.setBranch(tempB.get());
			encounter.setPatient(tempP.get());
			encounterRepo.save(encounter);
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(
					new Status(200, "Encounter added for Patient with ID-" + pid + " in Branch ID-" + bid, now),
					HttpStatus.OK);
		}
	}

	// update encounter
	public ResponseEntity<Status> updateEncounter(Encounter encounter) throws EncounterNotFoundException {
		Optional<Encounter> etemp = encounterRepo.findById(encounter.getEncounterId());
		if (etemp.isEmpty()) {
			throw new EncounterNotFoundException("No encounter with ID-" + encounter.getEncounterId() + " exists");
		} else {// wrong
				// encounterRepo.save(encounter);

			// correct
			etemp.get().setEncounterCause(encounter.getEncounterCause());
			etemp.get().setEncounterBloodGroup(encounter.getEncounterBloodGroup());
			// updating encounter
			encounterRepo.save(etemp.get());

			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(
					new Status(200, "Encounter with ID-" + encounter.getEncounterId() + " updated", now),
					HttpStatus.OK);
		}
	}

	// Delete by ID
	public ResponseEntity<Status> deleteById(int eid) throws EncounterNotFoundException {
		Optional<Encounter> etemp = encounterRepo.findById(eid);
		if (etemp.isEmpty()) {
			throw new EncounterNotFoundException("No encounter with ID-" + eid + " exists");
		}

		encounterRepo.deleteById(eid);
		LocalDateTime now = LocalDateTime.now();
		return new ResponseEntity<Status>(new Status(200, "Encounter with ID-" + eid + " deleted", now), HttpStatus.OK);

	}

	// Search by blood group

	public List<Encounter> findByBloodGroup(String bloodGroup) {
		return encounterRepo.findByEncounterBloodGroup(bloodGroup);
	}

	// search by patient object
	public List<Encounter> findByPatientObj(Patient patient) {
		return encounterRepo.findByPatient(patient);
	}

}
