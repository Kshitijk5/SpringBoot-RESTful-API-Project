package com.main.HospitalProjectREST.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	private String patientName;
	@Column(unique = true)
	private String patientEmail;

	@OneToMany(mappedBy = "patient")
	@Cascade({CascadeType.ALL})
	
	private List<Encounter> encounters;

	public Patient() {
		// default
	}

	public Patient(String patientName, String patientEmail) {
		// parameterized

		this.patientName = patientName;
		this.patientEmail = patientEmail;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

	public void addEncounters(Encounter e) {
		if (encounters == null) {
			encounters = new ArrayList<Encounter>();

		}
		encounters.add(e);
	}

}
