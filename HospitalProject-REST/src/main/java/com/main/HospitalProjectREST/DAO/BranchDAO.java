package com.main.HospitalProjectREST.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.main.HospitalProjectREST.CustomExceptions.BranchNotFoundException;
import com.main.HospitalProjectREST.CustomExceptions.HospitalNotFoundException;
import com.main.HospitalProjectREST.Repositories.BranchRepo;
import com.main.HospitalProjectREST.Repositories.HospitalRepo;
import com.main.HospitalProjectREST.entities.Branch;
import com.main.HospitalProjectREST.entities.Hospital;
import com.main.HospitalProjectREST.status.Status;


@Repository
public class BranchDAO {
  
	@Autowired 
	private BranchRepo branchRepo;
	
	@Autowired
	private HospitalRepo hospitalRepo;
	
	//List all branches
	public List<Branch> listAllBranches(){
		return branchRepo.findAll();
	}
	
	// Get branch by ID
		public Optional<Branch> getBranchDetailById(int branchId) {
			return branchRepo.findById(branchId);
		}

		//adding branch
		public ResponseEntity<Status> addBranch(Branch branch, int hid) throws HospitalNotFoundException {
			///check if hospital exists or not
			
			         Optional<Hospital> temp =  hospitalRepo.findById(hid);
			         if(temp.isEmpty()) {
			        	 throw new HospitalNotFoundException("No such Hospital with ID-"+hid+" exists,Cant add branch for non existing hospital");
			         }
			         else {
			        	 branch.setHospital(temp.get());
			        	 branchRepo.save(branch);
			        	 LocalDateTime now = LocalDateTime.now();
			        	 return new ResponseEntity<Status>(new Status(200,"Branch added for hospital with ID-"+hid,now),HttpStatus.OK);
			        	 
			         }     
			
		}
//Update branch
		public ResponseEntity<Status> updateBranch(Branch branch) throws BranchNotFoundException {
			 //Check whether if branch exists or not
			Optional<Branch> tempBranch = branchRepo.findById(branch.getBranchId());
			
			if(tempBranch.isEmpty()) {
				throw new BranchNotFoundException("No branch with ID-"+branch.getBranchId()+" found in database,Cant update");
			}
			else {
				//correct
				 tempBranch.get().setBranchAddress(branch.getBranchAddress());
				 tempBranch.get().setBranchName(branch.getBranchName());
				 tempBranch.get().setBranchMail(branch.getBranchMail());
				 branchRepo.save(tempBranch.get());
			//wrong----->	branchRepo.save(branch);
				 LocalDateTime now = LocalDateTime.now();
				return new ResponseEntity<Status>(new Status(200,"Branch with ID-"+branch.getBranchId()+" updated", now),HttpStatus.OK);
			}
			
		}
		
		
		//Delete branch
		
		public ResponseEntity<Status> deleteBranchById(int bid) throws BranchNotFoundException {
			Optional<Branch> temp = branchRepo.findById(bid);
			if (!temp.isEmpty()) {
				temp.get().setHospital(null);
				branchRepo.deleteById(bid);
				LocalDateTime now = LocalDateTime.now();
				return new ResponseEntity<Status>(new Status(200, "Branch with ID-" + bid + " deleted", now),
						HttpStatus.OK);
			} else {
			 throw new BranchNotFoundException("No branch with ID-"+bid+" exists,cant delete");

			}
		}
		
		
		
	
	
}
