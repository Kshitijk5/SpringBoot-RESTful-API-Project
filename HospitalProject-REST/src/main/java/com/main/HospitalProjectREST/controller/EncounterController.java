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
import com.main.HospitalProjectREST.CustomExceptions.EncounterNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.PatientNotFoundException;
import com.main.HospitalProjectREST.DAO.EncounterDAO;
import com.main.HospitalProjectREST.entities.Encounter;
import com.main.HospitalProjectREST.entities.Patient;
import com.main.HospitalProjectREST.status.Status;

@RestController
@RequestMapping("/encounter")
public class EncounterController {
	
	@Autowired
	private EncounterDAO encounterDAO;
	
	//List All patients
	@GetMapping("/")
	public List<Encounter> listAll(){
		return encounterDAO.listAllEncounters();
	}
  
	//Get encounter by ID
	@GetMapping("/{id}")
	public Encounter getEncounterById(@PathVariable("id") int eid) throws EncounterNotFoundException {
		return encounterDAO.getEncounterById(eid);
	}
	
	//Adding encounter to an existing patient
	@PostMapping("/{pid}/{bid}")
	public ResponseEntity<Status> addEncounter(@PathVariable int pid,@PathVariable int bid,@RequestBody Encounter encounter) throws PatientNotFoundException, BranchNotFoundException{
		return encounterDAO.addEncounter(pid, bid, encounter);
	}
	
	//Updating Encounter
	
	@PutMapping("/")
	public ResponseEntity<Status> updateEncounter(@RequestBody Encounter encounter) throws EncounterNotFoundException{
		return encounterDAO.updateEncounter(encounter);
	}
	
	//Deleting an Encounter
	@DeleteMapping("/{eid}")
	public ResponseEntity<Status> deleteEncounterById(@PathVariable("eid") int eid) throws EncounterNotFoundException{
		return encounterDAO.deleteById(eid);
	}
	
	//Search By blood group
	@GetMapping("/bloodgroup/{bloodGroup}")
	public List<Encounter> getByBloodGroup(@PathVariable("bloodGroup") String bloodGroup){
		return encounterDAO.findByBloodGroup(bloodGroup);
	}
	
	//Search By patient object
	@GetMapping("/patient")
	public List<Encounter> getByPatient(@RequestBody Patient p){
		return encounterDAO.findByPatientObj(p);
	}
}
