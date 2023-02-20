package com.main.HospitalProjectREST.CustomExceptions;

public class BranchNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BranchNotFoundException(String message) {
		super(message);
	}

}
