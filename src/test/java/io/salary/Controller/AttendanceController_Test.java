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

import io.salary.Attendance.Attendance;
import io.salary.Attendance.AttendanceService;
import io.salary.Department.Department;


@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "io.salary")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {AttendanceController_Test.class})
class AttendanceController_Test {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AttendanceService attendanceService;
	
	List<Attendance> attendances;
	Attendance attendance;
	
	@Test
	@Order(1)
	void test_GetAllAttendances() throws Exception {
	
		attendances = new ArrayList<Attendance>();
		attendances.add(new Attendance(29,9,2021,"emp001"));
		attendances.add(new Attendance(30,9,2021,"emp002"));
	
		when(attendanceService.getAllAttendances()).thenReturn(attendances);
		
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/attendance")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(2)
	void test_GetAttendanceById() throws Exception {
		
		attendance=new Attendance(29,9,2021,"emp001");
		when(attendanceService.getAttendance("emp001")).thenReturn(attendance);
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/attendance/{id}","emp001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	@Order(3)
	void test_DeleteAttendanceById() throws Exception {
			
		attendance=new Attendance(29,9,2021,"emp001");
		when(attendanceService.deletebyId("emp001")).thenReturn(attendance);
		RequestBuilder builder =MockMvcRequestBuilders
				.delete("/attendance/{id}","emp001")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	@Order(4)
	void test_UpdateAttendanceById() throws Exception {
		attendance=new Attendance(29,9,2021,"emp001");
        when(attendanceService.getAttendance("emp001")).thenReturn(attendance);
		RequestBuilder builder =  MockMvcRequestBuilders
				.put("/attendance/{id}","emp001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	
	}
	@Test
	@Order(5)
	void test_AddAttendance() throws Exception {
	
		attendance=new Attendance(29,9,2021,"emp001");
		when(attendanceService.addAttendance(attendance)).thenReturn(attendance);

		RequestBuilder builder = MockMvcRequestBuilders
				.post("/attendance")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getContentAsString());
	}
}
