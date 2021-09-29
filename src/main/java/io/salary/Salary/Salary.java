package io.salary.Salary;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Salary {
	@Id
	private String employeeId;
	private int actualsalary;	
	
	public Salary() {
		
	}

public Salary(int actualsalary, String employeeId) {
		super();
		this.actualsalary = actualsalary;
		this.employeeId = employeeId;
	}

public int getActualsalary() {
	return actualsalary;
}
public void setActualsalary(int actualsalary) {
	this.actualsalary = actualsalary;
}
public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}


}

	
    