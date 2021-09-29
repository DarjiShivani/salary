package io.salary.Employee;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import io.salary.Attendance.Attendance;
import io.salary.Department.Department;
import io.salary.Salary.Salary;

@Entity

public class Employee {
	@Id
	private String employeeId;
	private String employeeName;
	
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private String dob;
	
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private String doj;
	//private String departmentId;
	//private int actualsalary;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Attendance attendance;
	@OneToOne(cascade = CascadeType.ALL)
	private Salary salary;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="department_id",referencedColumnName = "departmentId")
	private Department department;
	
	
	public Employee()
	{
	}	
	public Employee(String employeeId, String employeeName, String dob, String doj,Salary salary,Department department,Attendance attendance) {
	
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.dob = dob;
		this.doj = doj;
		//this.departmentId = departmentId;
		this.salary=salary;
		this.department=department;
		this.attendance=attendance;
	}
	public Salary getSalary() {
		return salary;
	}
	 public void setSalary(Salary salary)
	{ 
		this.salary = salary;
	}
	 public Department getdepartment() {
		 return department;
	 }
	 public void setDepartment(Department department) {
		this.department=department;
	}
	 
	 public Attendance getAttendance() {
		 return attendance;
	 }
	 public void setAttendance(Attendance attendance) {
		 this.attendance=attendance;
	 }
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	
	
}
