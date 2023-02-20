package com.main.HospitalProjectREST.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.HospitalProjectREST.entities.Encounter;
import com.main.HospitalProjectREST.entities.Patient;

public interface EncounterRepo extends JpaRepository<Encounter,Integer> {

	public List<Encounter> findByEncounterBloodGroup(String bloodGroup);

	public List<Encounter> findByPatient(Patient patient);

}
