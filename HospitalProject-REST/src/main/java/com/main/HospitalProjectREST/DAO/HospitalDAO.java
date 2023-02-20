package com.main.HospitalProjectREST.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.main.HospitalProjectREST.Repositories.BranchRepo;
import com.main.HospitalProjectREST.Repositories.HospitalRepo;
import com.main.HospitalProjectREST.entities.Branch;
import com.main.HospitalProjectREST.entities.Hospital;
import com.main.HospitalProjectREST.status.Status;

@Repository
public class HospitalDAO {

	@Autowired
	private HospitalRepo hospitalRepo;

	@Autowired
	private BranchRepo branchRepo;

	// Lists All the hospitals
	public List<Hospital> listHospitals() {
		return hospitalRepo.findAll();
	}

	// Get hospital by ID
	public Optional<Hospital> getHospitalDetailById(int hospitalId) {
		return hospitalRepo.findById(hospitalId);
	}

	// Adds an hospital with branches to the database
	public Hospital addHospital(Hospital hospital) {
		
		List<Branch> listofbranches = hospital.getBranches();
	    List<Branch> list = new ArrayList<>();
	
	    if(listofbranches.isEmpty())
	    {
	    	hospital = hospitalRepo.save(hospital);
	    	
	    }
	    else
	    {
	    	hospital = hospitalRepo.save(hospital);
	    	for(Branch branch : listofbranches) {
	    		branch.setHospital(hospital);
	    		list.add(branchRepo.save(branch));
	    	}
	    	hospital.setBranches(list);
	    }
	    return hospital;
    
		
	}

	//Update hospital
	public ResponseEntity<Status> updateHospital(Hospital hospital) {
		Optional<Hospital> temp = hospitalRepo.findById(hospital.getHospitalId());

		if (!temp.isEmpty()) {
			temp.get().setHospitalAddress(hospital.getHospitalAddress());
			temp.get().setHospitalName(hospital.getHospitalName());
			temp.get().setHospitalPhno(hospital.getHospitalPhno());

			hospitalRepo.save(temp.get());
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(new Status(200, "Details updated", now), HttpStatus.OK);
		} else {
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(
					new Status(404, "No such hospital with ID-" + hospital.getHospitalId() + " found to update", now),
					HttpStatus.NOT_FOUND);
		}
	}

	//Delete hospital
	public ResponseEntity<Status> deleteHospital(int id) {
		Optional<Hospital> temp = hospitalRepo.findById(id);
		if (!temp.isEmpty()) {
			hospitalRepo.deleteById(id);
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(new Status(200, "Hospital with ID-" + id + " deleted", now),
					HttpStatus.OK);
		} else {
			LocalDateTime now = LocalDateTime.now();
			return new ResponseEntity<Status>(
					new Status(404, "No such hospital with ID-" + id + " found,Cant delete", now),
					HttpStatus.NOT_FOUND);

		}
	}

}
