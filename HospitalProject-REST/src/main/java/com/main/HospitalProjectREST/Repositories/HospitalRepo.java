package com.main.HospitalProjectREST.Repositories;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.HospitalProjectREST.entities.Hospital;
//import com.main.HospitalProjectREST.status.Status;

public interface HospitalRepo extends JpaRepository<Hospital,Integer> {
	
//	public List<Hospital> listHospitals();
//	public Hospital getHospitalDetail(int hospitalId);
//	public Status addHospital(Hospital hospital);
//	public Status updateHospital(Hospital hospital);
//	public Status deleteHospital(Hospital hospital);
        
}
