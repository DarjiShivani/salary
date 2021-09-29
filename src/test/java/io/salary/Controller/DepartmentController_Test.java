package io.salary.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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

import io.salary.Attendance.Attendance;
import io.salary.Department.Department;
import io.salary.Department.DepartmentService;
import io.salary.Employee.Employee;
import io.salary.Salary.Salary;


@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "io.salary")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {DepartmentController_Test.class})
class DepartmentController_Test {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	DepartmentService departmentService;
	
	List<Department> departments;
	Department  department;
	
	@Test
	@Order(1)
	void test_GetAllDepartments() throws Exception {
	
		departments = new ArrayList<Department>();
		departments.add(new Department("dept001","java"));
		departments.add(new Department("dept002","javascript"));
	
		when(departmentService.getAllDepartments()).thenReturn(departments);
		
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/departments")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(2)
	void test_GetDepartmentByName() throws Exception {
		
		department=new Department("dept001","java");
		when(departmentService.getDepartment("java")).thenReturn(department);
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/departments/{name}","java")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	@Order(3)
	void test_DeleteDepartmentByName() throws Exception {
			
		department=new Department("dept001","java");
		when(departmentService.deletebyName("java")).thenReturn(department);
		RequestBuilder builder =MockMvcRequestBuilders
				.delete("/departments/{name}","java")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(4)
	void test_UpdateDepartmentByName() throws Exception {
		

	
		department=new Department("dept001","java");
        when(departmentService.getDepartment("java")).thenReturn(department);
		RequestBuilder builder =  MockMvcRequestBuilders
				.put("/departments/{name}","java")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	
	}
	@Test
	@Order(5)
	void test_AddDepartment() throws Exception {
	
		department=new Department("dept001","java");
		when(departmentService.addDepartment(department)).thenReturn(department);

		RequestBuilder builder = MockMvcRequestBuilders
				.post("/departments")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
}
