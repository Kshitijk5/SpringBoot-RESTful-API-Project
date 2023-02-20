package com.main.HospitalProjectREST.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.HospitalProjectREST.CustomExceptions.BranchNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.HospitalNotFoundException;
import com.main.HospitalProjectREST.DAO.BranchDAO;
import com.main.HospitalProjectREST.entities.Branch;
import com.main.HospitalProjectREST.status.Status;

@RestController

@RequestMapping("/branch")
public class BranchController {

	@Autowired
	private BranchDAO branchDao;

	// list all branches
	@GetMapping("/")
	public List<Branch> listAll() {
		return branchDao.listAllBranches();
	}

	// Get branch by id
	@GetMapping("/{id}")
	public Branch getBranchById(@PathVariable("id") int id) throws BranchNotFoundException {
		Optional<Branch> temp = branchDao.getBranchDetailById(id);
		if (temp.isEmpty()) {
			throw new BranchNotFoundException("No branch with ID-" + id + " exists");
		} else {
			return temp.get();
		}
	}

	// Adding branch
	@PostMapping("/{hospitalId}")
	public ResponseEntity<Status> addBranch(@RequestBody Branch branch, @PathVariable("hospitalId") int hid) throws HospitalNotFoundException {

		return branchDao.addBranch(branch, hid);

	}
	
	
	//Update branch
	@PutMapping("/")
	public ResponseEntity<Status> updateBranch(@RequestBody Branch branch) throws BranchNotFoundException{
		
		return branchDao.updateBranch(branch);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Status> deleteBranch(@PathVariable("id") int bid) throws BranchNotFoundException{
		
		return branchDao.deleteBranchById(bid);
	}

}
