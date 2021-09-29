package io.salary.Attendance;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Attendance {
	
	@Id
	private String employeeId;
	private int month;
	private int year;
	private int working_days;
	
	public Attendance() {
		
	}
public Attendance(int month, int year, int working_days, String employeeId) {
		super();
		this.month = month;
		this.year = year;
		this.working_days = working_days;
		this.employeeId = employeeId;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getWorking_days() {
		return working_days;
	}
	public void setWorking_days(int working_days) {
		this.working_days = working_days;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
}	
    