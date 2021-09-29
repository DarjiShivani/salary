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

import io.salary.Attendance.Attendance;
import io.salary.Attendance.AttendanceRepository;
import io.salary.Attendance.AttendanceService;
import io.salary.Department.Department;


@SpringBootTest
class AttendanceService_Test {
	
	@Autowired
	private AttendanceService attendanceService;
	
	
	@MockBean
	private AttendanceRepository attendanceRepository;
	
	
	@Test
	public void adddAttendance() {
		
		
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		
		when(this.attendanceRepository.save(attendance)).thenReturn(attendance);
		assertEquals(attendance,attendanceService.addAttendance(attendance));
		assertEquals(attendance.getEmployeeId(),attendanceService.addAttendance(attendance).getEmployeeId());
		
	System.out.println(	"\n Employeeid : " + attendance.getEmployeeId() +
			"\n Working days : " + attendance.getWorking_days()+
			"\n Month : "+attendance.getMonth()+
			"\n Year : "+attendance.getYear());
	
	}
	@Test
	public void deleteAttendance()
	{
		
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		attendanceRepository.delete(attendance);
		verify(attendanceRepository,times(1)).delete(attendance);
		System.out.println("deleted the attendance of employee " +attendance.getEmployeeId());
	}
	@Test
	public void getAllAttendance() {	
		List<Attendance> attendance=new ArrayList<Attendance>();		
		
		attendance.add(new Attendance(8,2021,29,"emp001"));
		attendance.add(new Attendance(8,2021,30,"emp002"));
		when(attendanceRepository.findAll()).thenReturn(attendance);
		assertEquals(2, attendanceService.getAllAttendances().size());	
		attendance.stream().forEach(att -> System.out.println("---------------------------------------"+("\n")+
					"Employee id is: "+att.getEmployeeId()+("\n")+
				        " Month : "+att.getMonth()+("\n")+
				        "Year : "+att.getYear()+("\n")+
				        "Working_days : "+att.getWorking_days()+("\n")+ 
				        "----------------------------------------------------"));
		
		}
	@Test
	public void getAttendanceById() {
		
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		when(attendanceRepository.findByEmployeeId("emp001")).thenReturn(attendance);
		assertSame(attendance, attendanceService.getAttendance("emp001"));	
		verify(attendanceRepository).findByEmployeeId("emp001");
		}
	@Test
	public void updateDepartmentByName() {
			
		Attendance attendance=new Attendance(8,2021,29,"emp001");
		Attendance attendance1=new Attendance(8,2021,30,"emp001");
			when(attendanceRepository.findByEmployeeId("emp001")).thenReturn(attendance1);
			attendanceService.updateAttendance("emp001",attendance1);
			assertSame(attendance1, attendanceService.getAttendance("emp001"));	
		
		   	} 

}
		
