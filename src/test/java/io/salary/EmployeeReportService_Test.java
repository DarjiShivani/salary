package io.salary;


import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.salary.Attendance.Attendance;
import io.salary.Department.Department;
import io.salary.Employee.Employee;
import io.salary.Employee.EmployeeRepository;
import io.salary.Employee.EmployeeService;
import io.salary.Salary.Salary;

@SpringBootTest
class EmployeeReportService_Test {
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	
	@Test
	public void getEmployeeReport() {
		Department department=new Department("dept001","java");
		Department department1=new Department("dept002","javascript");
		Salary salary=new Salary(30000,"emp001");
		Salary salary1 = new Salary(25000,"emp001");
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		Attendance attendance1=new Attendance(8,2021,30,"emp002");
		List<Employee> employee=new ArrayList<Employee>();	
		
		employee.add(new Employee("empno1","Shivani","20-10-2012","22-04-2021",salary,department,attendance));
		employee.add(new Employee("empno2","Himali","20-10-2012","22-04-2021",salary1,department1,attendance1));
		when(employeeRepository.findAll()).thenReturn(employee);
		assertEquals(2, employeeService.getAllEmployees().size());	
		employee.stream().forEach(emp -> System.out.println("---------------------------------------"+("\n")+
				        "employee ID is : "+emp.getEmployeeId()+("\n")+
				        "Employee name is : "+emp.getEmployeeName()+("\n")+
				        "Employee Date of Birth is : "+emp.getDob()+("\n")+
				        "Employee Date of Joining is : "+emp.getDoj()+("\n")+
				        "Employee's department name is : "+emp.getdepartment().getDepartmentName()+("\n")+
				        "----------------------------------------------------"));
	}	
	
	@Test
	public void getSalaryReport() {
		Department department=new Department("dept001","java");
		Department department1=new Department("dept002","javascript");
		Salary salary=new Salary(30000,"emp001");
		Salary salary1 = new Salary(25000,"emp001");
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		Attendance attendance1=new Attendance(8,2021,30,"emp002");
		List<Employee> employee=new ArrayList<Employee>();	
		 Calendar c = Calendar.getInstance();
	      int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	      int totalsalary=(salary.getActualsalary()/monthMaxDays)*attendance.getWorking_days();
		
		employee.add(new Employee("empno1","Shivani","20-10-2012","22-04-2021",salary,department,attendance));
		employee.add(new Employee("empno2","Himali","20-10-2012","22-04-2021",salary1,department1,attendance1));
		when(employeeRepository.findAll()).thenReturn(employee);
		assertEquals(2, employeeService.getAllEmployees().size());	
		employee.stream().forEach(emp -> System.out.println("---------------------------------------"+("\n")+
				        "employee ID is : "+emp.getEmployeeId()+("\n")+
				        "Employee name is : "+emp.getEmployeeName()+("\n")+
				        "Month : " +  attendance.getMonth() + ("\n")+
				        "Year : " + attendance.getYear() +("\n")+
				        "Actual salary : " + salary.getActualsalary()+("\n")+
				 	    "calculated salary : " + totalsalary+("\n")+
				        "----------------------------------------------------"));
		
		
	}

}
		
