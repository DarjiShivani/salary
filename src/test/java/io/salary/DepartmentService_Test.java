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
import io.salary.Department.Department;
import io.salary.Department.DepartmentRepository;
import io.salary.Department.DepartmentService;
import io.salary.Employee.Employee;
import io.salary.Employee.EmployeeRepository;
import io.salary.Employee.EmployeeService;
import io.salary.Salary.Salary;

@SpringBootTest
class DepartmentService_Test {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	
	@Test
	public void addDepartment() {
		
		
		Department department=new Department("dept001","java");
		when(this.departmentRepository.save(department)).thenReturn(department);
	   
		assertEquals(department,departmentService.addDepartment(department));
		assertEquals(department.getDepartmentName(),departmentService.addDepartment(department).getDepartmentName());
		
	System.out.println(	"\n DepartmentId: " + department.getDepartmentId() +
			"\n Departmentname: " + department.getDepartmentName());
	}
	@Test
	public void deleteDepartment()
	{
		
		Department department=new Department("dept001","java");
		departmentRepository.delete(department);
		verify(departmentRepository,times(1)).delete(department);
		System.out.println("deleted the department");
	}
	@Test
	public void getAllDepartment() {	
		List<Department> department=new ArrayList<Department>();		
		
		department.add(new Department("dept001","java"));
		department.add(new Department("dept002","javascript"));
		when(departmentRepository.findAll()).thenReturn(department);
		assertEquals(2, departmentService.getAllDepartments().size());	
		department.stream().forEach(dep -> System.out.println("---------------------------------------"+("\n")+
					"department id is: "+dep.getDepartmentId()+("\n")+
				        " department name is : "+dep.getDepartmentName()+("\n")+
				        "----------------------------------------------------"));
	}
	@Test
	public void getDepartmentByName() {
		
		Department department=new Department("dept001","java");
		when(departmentRepository.findByDepartmentName("java")).thenReturn(department);
		assertSame(department, departmentService.getDepartment("java"));	
		verify(departmentRepository).findByDepartmentName("java");
		}
	@Test
	public void updateDepartmentByName() {
			
		Department department=new Department("dept001","java");
		Department department1=new Department("dept001","javascript");
			when(departmentRepository.findByDepartmentName("java")).thenReturn(department1);
			departmentService.updateTopic("Shivani",department1);
			assertSame(department1, departmentService.getDepartment("java"));	
		
		   	} 

}
		
