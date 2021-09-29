package io.salary.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.salary.Attendance.Attendance;
import io.salary.Attendance.AttendanceService;
import io.salary.Department.Department;
import io.salary.Department.DepartmentService;
import io.salary.Employee.Employee;
import io.salary.Employee.EmployeeService;
import io.salary.Salary.Salary;
import io.salary.Salary.SalaryService;


@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "io.salary")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {EmployeeReportController_Test.class})
class EmployeeReportController_Test {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	@MockBean
	DepartmentService departmentService;
	@MockBean
	AttendanceService attendanceService;
	@MockBean
	SalaryService salaryService;


	
	List<Employee> employees;
	Employee  employee;

	@Test
	@Order(1)
	void test_GetEmployeeReport() throws Exception {
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");

		//employees = new ArrayList<Employee>();
		employee=new Employee("emp001","shivani","23-09-1999","23-09-2020",salary,department,attendance);
	
		when(employeeService.getEmployee("shivani")).thenReturn(employee);
		
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/employees/information/{name}","shivani")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(2)
	public void test_GetSalaryReport() throws Exception {
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		
		Calendar c = Calendar.getInstance();
	      int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	      int totalsalary=(salary.getActualsalary()/monthMaxDays)*attendance.getWorking_days();
		
	      employee=new Employee("emp001","shivani","23-09-1999","23-09-2020",salary,department,attendance);	
	      when(employeeService.getEmployee("shivani")).thenReturn(employee);
	      when(salaryService.getSalary("emp001")).thenReturn(salary);
	      when(attendanceService.getAttendance("emp001")).thenReturn(attendance);
		RequestBuilder builder =  MockMvcRequestBuilders
				.get("/employees/salary_information/{name}/{id}/{id}","shivani","emp001","emp001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
		
		
	}
}
