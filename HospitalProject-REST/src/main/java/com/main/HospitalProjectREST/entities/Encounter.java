package com.main.HospitalProjectREST.entities;


import javax.persistence.Entity;
//import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Encounter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int encounterId;
	private String encounterCause;
	private String encounterBloodGroup;

	@ManyToOne
	@JoinColumn(name = "branch_id")
	
	@Cascade({CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private Branch branch;

	@ManyToOne
	
	@JoinColumn(name = "patient_id")
	@JsonIgnore
	@Cascade({CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	private Patient patient;

//	@OneToMany(mappedBy = "encounter")
//	@Cascade(CascadeType.ALL)
//	private List<MedOrder> medOrders;

	public Encounter() {
	}

	public Encounter(String encounterCause, String encounterBloodGroup) {

		this.encounterCause = encounterCause;
		this.encounterBloodGroup = encounterBloodGroup;
	}

	public Encounter(String encounterCause, String encounterBloodGroup, Branch branch) {

		this.encounterCause = encounterCause;
		this.encounterBloodGroup = encounterBloodGroup;
		this.branch = branch;

	}

	public int getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}

	public String getEncounterCause() {
		return encounterCause;
	}

	public void setEncounterCause(String encounterCause) {
		this.encounterCause = encounterCause;
	}

	public String getEncounterBloodGroup() {
		return encounterBloodGroup;
	}

	public void setEncounterBloodGroup(String encounterBloodGroup) {
		this.encounterBloodGroup = encounterBloodGroup;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

//	public List<MedOrder> getMedOrders() {
//		return medOrders;
//	}

//
//	public void setMedOrders(List<MedOrder> medOrders) {
//		this.medOrders = medOrders;
//	}
//	

}
