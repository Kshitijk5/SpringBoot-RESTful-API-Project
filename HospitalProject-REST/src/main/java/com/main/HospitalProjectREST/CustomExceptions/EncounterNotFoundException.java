package com.main.HospitalProjectREST.CustomExceptions;

public class EncounterNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EncounterNotFoundException(String message) {
		super(message);
	}

}
