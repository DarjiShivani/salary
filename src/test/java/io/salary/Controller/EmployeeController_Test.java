package io.salary.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Result;

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
@SpringBootTest(classes = {EmployeeController_Test.class})
class EmployeeController_Test {
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
	void test_GetAllEmployee() throws Exception {
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");

		employees = new ArrayList<Employee>();
		employees.add(new Employee("emp001","shivani","23-09-1999","23-09-2020",salary,department,attendance));
	
		when(employeeService.getAllEmployees()).thenReturn(employees);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders
				.get("/employees")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(2)
	void test_GetEmployeeByName() throws Exception {
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");

	
		employee=new Employee("emp001","shivani","23-09-1999","23-09-2020",salary,department,attendance);
		when(employeeService.getEmployee("shivani")).thenReturn(employee);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders
				.get("/employees/{name}","shivani")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	@Order(3)
	void test_DeleteEmployeeByName() throws Exception {
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");

	
		employee=new Employee("emp001","shivani","23-09-1999","23-09-2020",salary,department,attendance);
		when(employeeService.deletebyName("shivani")).thenReturn(employee);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders
				.delete("/Employees/{name}","shivani")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(4)
	void test_UpdateEmployee() throws Exception {
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");

	
		employee=new Employee("emp001","shivani","23-09-1999","23-09-2020",salary,department,attendance);
        when(employeeService.getEmployee("shivani")).thenReturn(employee);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders
				.put("/employees/{name}","shivani")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	
	}
	
	@Test
	@Order(5)
	void test_AddEmployee() throws Exception {
		Salary salary =new Salary(30000,"emp001");
		Department department=new Department("dept001","java");
		Attendance attendance=new Attendance(8,2021,29,"emp001");

	
		employee=new Employee("emp001","shivani","23-09-1999","23-09-2020",salary,department,attendance);
		Mockito.when(employeeService.addEmployee(employee)).thenReturn(employee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/employees")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	
}

