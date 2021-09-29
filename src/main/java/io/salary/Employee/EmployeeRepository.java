package io.salary.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.salary.Attendance.Attendance;
import io.salary.Salary.Salary;



public interface EmployeeRepository extends JpaRepository<Employee,String>{
	public Employee findByEmployeeName(String EmployeeName);
	
	@Query("select a from Attendance a where employeeId= :employeeId")
	public Attendance findByAttendanceEmployeeId(String employeeId);
	
	@Query("select s from Salary s where id= :id ")
	public Salary findBySalaryId(String id);
	
}
