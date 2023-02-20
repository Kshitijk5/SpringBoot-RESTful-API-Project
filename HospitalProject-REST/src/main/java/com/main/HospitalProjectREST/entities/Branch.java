package com.main.HospitalProjectREST.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchId;
	private String branchName;
	private String branchAddress;
	private String branchMail;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn
	private Hospital hospital;


//	@Override
//	public String toString() {
//		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchAddress=" + branchAddress
//				+ ", branchMail=" + branchMail + ", hospital=" + hospital + ", encounters=" + encounters + "]";
//	}

	@OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
	@JsonIgnore
	List<Encounter> encounters;



	public Branch() {

	}

	public Branch(String branchName, String branchAddress, String branchMail) {

		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.branchMail = branchMail;
	}

	public Branch(int branchId, String branchName, String branchAddress, String branchMail, Hospital hospital,
			List<Encounter> encounters) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.branchMail = branchMail;
		this.hospital = hospital;
		this.encounters = encounters;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchMail() {
		return branchMail;
	}

	public void setBranchMail(String branchMail) {
		this.branchMail = branchMail;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

	

}
