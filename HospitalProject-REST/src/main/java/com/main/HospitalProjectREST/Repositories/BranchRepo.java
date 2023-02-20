package com.main.HospitalProjectREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.HospitalProjectREST.entities.Branch;

public interface BranchRepo extends JpaRepository<Branch,Integer> {

}
