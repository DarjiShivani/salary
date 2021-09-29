package io.salary;


import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.salary.Attendance.Attendance;
import io.salary.Attendance.AttendanceRepository;
import io.salary.Attendance.AttendanceService;
import io.salary.Department.Department;
import io.salary.Department.DepartmentRepository;
import io.salary.Department.DepartmentService;
import io.salary.Employee.Employee;
import io.salary.Employee.EmployeeController;
import io.salary.Employee.EmployeeRepository;
import io.salary.Employee.EmployeeService;
import io.salary.Salary.Salary;
import io.salary.Salary.SalaryService;

@SpringBootTest
class EmployeeService_Test {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private DepartmentService departmentService;

	
	@MockBean
	private EmployeeRepository employeeRepository;
	@MockBean
	private AttendanceRepository attendanceRepository;
	@MockBean
	private DepartmentRepository departmentRepository;
	
	
	
	@Test
	public void addEmployee() {
		
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		
		Employee employee=new Employee("empno1","Shivani","20-10-2012","22-04-2021",salary,department,attendance);
				
		when(employeeRepository.save(employee)).thenReturn(employee);
	    //verify(employeeRepository,times(1)).save(employee);

		assertEquals(employee,employeeService.addEmployee(employee));
		assertEquals(employee.getEmployeeName(),employeeService.addEmployee(employee).getEmployeeName());
		
		
	System.out.println(	employee.getEmployeeName() + "\nEmployeeId : " + employee.getEmployeeId() + "\nEmployee Date of birth : " + 
		   employee.getDob() + "\nEmployee Date of join : " + employee.getDoj() + "\nEmployee Departmentname: " + employee.getdepartment().getDepartmentName());
	}
	
	@Test
	public void getAllEmployee() {
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
	public void deleteEmployee()
	{
		
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		Employee employee=new Employee("emp001","Shivani","23-09-1999","12-09-2020",salary,department,attendance);	
		employeeRepository.delete(employee);
		verify(employeeRepository,times(1)).delete(employee);
		System.out.println("deleted the employee");
	}
	@Test
	public void getEmployeeByName() {
			Department department=new Department("dept001","java");
			Salary salary=new Salary(30000,"emp001");
			Attendance attendance=new Attendance(8,2021,29,"emp001");
			Employee employee=new Employee("emp001","Shivani","23-09-1999","12-09-2020",salary,department,attendance);
			//employee.add(new Employee("empno2","Himali","20-10-2012","22-04-2021",salary1,department1,attendance1));
			when(employeeRepository.findByEmployeeName("Shivani")).thenReturn(employee);
			assertSame(employee, employeeService.getEmployee("Shivani"));	
			verify(employeeRepository).findByEmployeeName("Shivani");
	}
	@Test
	public void updateEmployeeByName() {
		
		Department department=new Department("dept001","java");
		Salary salary=new Salary(30000,"emp001");
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		Employee employee=new Employee("emp001","Shivani","23-09-1999","12-09-2020",salary,department,attendance);
		Employee employee1=new Employee("empno1","Shiv","20-10-2012","22-04-2021",salary,department,attendance);
		when(employeeRepository.findByEmployeeName("Shivani")).thenReturn(employee1);
		employeeService.updateEmployee("Shivani",employee1);
		assertSame(employee1, employeeService.getEmployee("Shivani"));	
	} 
	@Test
	public void setDepartmentToEmployee() {		
		Department department=new Department("dept001","java");
		Salary salary=new Salary(30000,"emp001");
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		Employee employee=new Employee("emp001","Shivani","23-09-1999","12-09-2020",salary,department,attendance);
		when(employeeRepository.findByEmployeeName("Shivani")).thenReturn(employee);
		employee.setDepartment(department);
	  	employeeService.save(employee);	
	}
	
}
	


		
