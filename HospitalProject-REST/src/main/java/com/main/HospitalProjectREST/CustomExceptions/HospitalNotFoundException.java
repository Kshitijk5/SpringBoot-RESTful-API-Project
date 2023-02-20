package com.main.HospitalProjectREST.CustomExceptions;

@SuppressWarnings("serial")
public class HospitalNotFoundException extends Exception {

	public HospitalNotFoundException(String message) {
		super(message);
	}

}
