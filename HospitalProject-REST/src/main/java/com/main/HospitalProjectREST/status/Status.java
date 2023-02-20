package com.main.HospitalProjectREST.status;

import java.time.LocalDateTime;


public class Status {

	private int status;
	private String message;

	private LocalDateTime time;

	public Status(int status, String message, LocalDateTime dateTime) {
		super();
		this.status = status;
		this.message = message;
		this.time = dateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return time;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.time = dateTime;
	}

	public Status() {
		
	}

	
}
