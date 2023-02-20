package com.main.HospitalProjectREST.controller;


import java.util.List;
import java.util.Optional;

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

import com.main.HospitalProjectREST.CustomExceptions.HospitalNotFoundException;
import com.main.HospitalProjectREST.DAO.HospitalDAO;
import com.main.HospitalProjectREST.entities.Hospital;
import com.main.HospitalProjectREST.status.Status;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
     
	@Autowired
	private HospitalDAO hospitalDao;
	
	@GetMapping("/")
	public List<Hospital> listHospital(){
		return hospitalDao.listHospitals();
	}
	
	@GetMapping("/{id}")
	public Hospital getHospitalById(@PathVariable("id") int id) throws HospitalNotFoundException {
		Optional<Hospital> temp = hospitalDao.getHospitalDetailById(id);
		if(temp.isEmpty()) {
			throw new HospitalNotFoundException("No hospital with ID-"+id+" Exists");
		}
		else
			return temp.get();
	}
	
	@PostMapping("/")
	public Hospital addHospital(@RequestBody Hospital h){
	         return hospitalDao.addHospital(h);
//	         LocalDateTime now = LocalDateTime.now();
//	        Status status = new  Status(HttpStatus.OK.value(),"Hospital Added",now);
//	        return new ResponseEntity<Status>(status,HttpStatus.BAD_GATEWAY);	
		
	}
	
	@PutMapping("/")
	public ResponseEntity<Status> updateHospital(@RequestBody Hospital h){
	          return  hospitalDao.updateHospital(h);			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Status> deleteHospitall(@PathVariable("id") int id){
        return  hospitalDao.deleteHospital(id);			
}
	
	
}
