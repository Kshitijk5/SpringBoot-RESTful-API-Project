package com.main.HospitalProjectREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.HospitalProjectREST.entities.Patient;

public interface PatientRepo extends JpaRepository<Patient,Integer> {

	Patient findByPatientEmail(String email);

}
