package io.salary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.salary.Attendance.Attendance;
import io.salary.Department.Department;
import io.salary.Department.DepartmentRepository;
import io.salary.Department.DepartmentService;
import io.salary.Employee.Employee;
import io.salary.Salary.Salary;
import io.salary.Salary.SalaryRepository;
import io.salary.Salary.SalaryService;

@SpringBootTest
class SalaryService_Test {
	
	@Autowired
	private SalaryService salaryService;
		
	@MockBean
	private SalaryRepository   salaryRepository;
		
	@Test
	public void addSalary() {
		
		
		Salary salary=new Salary(30000,"emp001");
		when(this.salaryRepository.save(salary)).thenReturn(salary);
	   
		assertEquals(salary,salaryService.addSalary(salary));
		assertEquals(salary.getEmployeeId(),salaryService.addSalary(salary).getEmployeeId());
		
	System.out.println(	"\n EmployeeId: " + salary.getEmployeeId() +
			"\n Actual Salary: " + salary.getActualsalary());
	}
	@Test
	public void deleteSalary()
	{
		
		Salary salary=new Salary(30000,"emp001");
		salaryRepository.delete(salary);
		verify(salaryRepository,times(1)).delete(salary);
		System.out.println("deleted the  Salary of employee"+salary.getEmployeeId());
	}
	@Test
	public void getAllSalary() {	
		List<Salary> salary=new ArrayList<Salary>();		
		
		salary.add(new Salary(30000,"emp001"));
		salary.add(new Salary(40000,"emp002"));
		when(salaryRepository.findAll()).thenReturn(salary);
		assertEquals(2, salaryService.getAllSalary().size());	
		salary.stream().forEach(sal -> System.out.println("---------------------------------------"+("\n")+
					"Employee id is: "+sal.getEmployeeId()+("\n")+
				        " Actual Salary is : "+sal.getActualsalary()+("\n")+
				        "----------------------------------------------------"));
	}
	@Test
	public void getSalaryById() {
		
		Salary salary=new Salary(30000,"emp001");
		when(salaryRepository.findByEmployeeId(Mockito.any())).thenReturn(salary);
		assertSame(salary, salaryService.getSalary("emp001"));	
		verify(salaryRepository).findByEmployeeId("emp001");
		}
	@Test
	public void updateSalaryByEmployeeId() {
			
			Salary salary=new Salary(30000,"emp001");
			Salary salary1=new Salary(25000,"emp001");
			when(salaryRepository.findByEmployeeId("emp001")).thenReturn(salary1);
			salaryService.updateSalary("Shivani",salary1);
			assertSame(salary1, salaryService.getSalary("emp001"));	
		
		   	} 
	}

