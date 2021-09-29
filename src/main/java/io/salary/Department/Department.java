package io.salary.Department;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.salary.Employee.Employee;

@Entity
public class Department {
	@Id
    private String departmentId;
    private String departmentName;
   
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new LinkedList<>();
    
	public Department() {
		
	}
	
    public Department(String departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}