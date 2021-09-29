package io.salary.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import io.salary.Department.Department;
import io.salary.Salary.Salary;
import io.salary.Salary.SalaryService;
@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "io.salary")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {SalaryController_Test.class})
class SalaryController_Test {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	SalaryService salaryService;
	
	List<Salary> salaries;
	Salary salary;
	@Test
	@Order(1)
	void test_GetAllSalary() throws Exception {
	
		salaries = new ArrayList<Salary>();
		salaries.add(new Salary(30000,"emp001"));
		salaries.add(new Salary(50000,"emp002"));
	
		when(salaryService.getAllSalary()).thenReturn(salaries);
		
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/salary")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(2)
	void test_GetSalaryById() throws Exception {
		
		salary = new Salary(50000,"emp002");
		when(salaryService.getSalary("emp001")).thenReturn(salary);
		
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/salary/{id}","emp001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	@Order(3)
	void test_DeleteSalaryById() throws Exception {
			
		salary = new Salary(50000,"emp002");
		when(salaryService.deletebyId("emp001")).thenReturn(salary);
		
		RequestBuilder builder =MockMvcRequestBuilders
				.delete("/salary/{id}","emp001")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(4)
	void test_UpdateSalary() throws Exception {
		
		salary = new Salary(50000,"emp002");
        when(salaryService.getSalary("emp001")).thenReturn(salary);
        
		RequestBuilder builder =  MockMvcRequestBuilders
				.put("/salary/{id}","emp001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	
	}
	@Test
	@Order(5)
	void test_AddSalary() throws Exception {
	
		salary = new Salary(50000,"emp002");
		when(salaryService.addSalary(salary)).thenReturn(salary);

		RequestBuilder builder = MockMvcRequestBuilders
				.post("/salary")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	

}
