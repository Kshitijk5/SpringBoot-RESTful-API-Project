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
public class Hospital {
//	@Override
//	public String toString() {
//		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", hospitalAddress="
//				+ hospitalAddress + ", hospitalPhno=" + hospitalPhno + ", branches=" + branches + "]";
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospitalId;
	//@Column(unique = true)
	private String hospitalName;
	private String hospitalAddress;
	private String hospitalPhno;
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "hospital")
	@JsonIgnore
	private List<Branch> branches;

	public Hospital() {
		// default
	}

	public Hospital( String hospitalName, String hospitalAddress, String hospitalPhno) {
		// parameterized
	
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.hospitalPhno = hospitalPhno;

	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalPhno() {
		return hospitalPhno;
	}

	public void setHospitalPhno(String hospitalPhno) {
		this.hospitalPhno = hospitalPhno;
	}


	
	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public void addBranches(Branch branch) {
		if(branches==null) {
			branches = new ArrayList<Branch>();
			}
		branches.add(branch);
	}

	

}

