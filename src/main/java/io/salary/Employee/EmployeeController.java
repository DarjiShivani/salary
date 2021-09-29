package io.salary.Employee;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.salary.Attendance.Attendance;
import io.salary.Salary.Salary;
import io.salary.Department.Department;
import io.salary.Attendance.AttendanceService;
import io.salary.Salary.SalaryService;
import io.salary.Department.DepartmentService;
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private SalaryService salaryService;

    @RequestMapping(method= RequestMethod.POST , value = "/employees")
    public void addEmployee(@ModelAttribute Employee employee){
        employeeService.addEmployee(employee);
    }
    @RequestMapping(method=RequestMethod.DELETE,value ="/employees/{name}")
	public void deleteEmployee(@PathVariable String name){
    	Employee employee = employeeService.getEmployee(name);
    	if(employee!=null) {
    		employeeService.deletebyName(name);
    	}
    	else {
    		throw new NoSuchElementException(); 
    	}
	employeeService.deletebyName(name);
	}
    
   
    @RequestMapping(method=RequestMethod.PUT,value ="/employees/{name}")
	public void updateEmployee(@RequestBody Employee employee,@PathVariable String name) {	
    	Employee employee1 =employeeService.getEmployee(name);
    	if(employee1!=null) {
    		employeeService.updateEmployee(name,employee1);
    	}
    	else {
    		throw new NoSuchElementException();
    	}
    	
   	} 
     @RequestMapping("/employees")
    public List<Employee> getAllEmployees(){
       return employeeService.getAllEmployees();
    }
  
    @RequestMapping("/employees/{name}")
	public Employee getEmployees(@PathVariable String  name) {
 		Employee employee =employeeService.getEmployee(name);
		if(employee!=null)
		{
			return employee;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
     
     @RequestMapping(method=RequestMethod.PUT,value ="/employees/set_salary/{name}/{id}")
   	public Employee setEmployeeSalary(@PathVariable String name,@PathVariable String id) {	
    	Employee employee=employeeService.getEmployee(name);
    	Salary salary = employeeService.getSalaryById(id);
    	if(employee!= null && salary!=null) {
    	employee.setSalary(salary);
    	employeeService.save(employee);
    	
    	return employeeService.getEmployee(name);
    	}else {
    		throw new NoSuchElementException();
      	} 
     } 
   @RequestMapping(method=RequestMethod.PUT,value ="/employees/set_attendance/{name}/{id}")
  	public Employee setEmployeeAttendance(@PathVariable String name,@PathVariable String id) {	
   	Employee employee=employeeService.getEmployee(name);
   	 Attendance attendance= employeeService.getAttendanceById(id);
   	if(employee!=null && attendance!=null) {
   	employee.setAttendance(attendance);
   	employeeService.save(employee);
   	
   	return employeeService.getEmployee(name);
   	}else {
   		throw new NoSuchElementException();
   	}
     	} 
   @RequestMapping(method=RequestMethod.PUT,value ="/{departmentName}/{employeeName}")
 	public Employee setDepartmentToEmployee(@PathVariable String departmentName,@PathVariable String employeeName) {	
  	Employee employee=employeeService.getEmployee(employeeName);
  	 Department department = departmentService.getDepartment(departmentName);
  	if(employee!=null && department!=null) {
  	employee.setDepartment(department);
  	employeeService.save(employee);
	return employee;
  	}
  	else {
  		throw new NoSuchElementException();
  	}
   } 
   
   @RequestMapping("/employees/information/{name}")
   public String getEmployee1(@PathVariable String name){
	   Employee employee = employeeService.getEmployee(name);
	   if(employee!=null) {
	   return employee.getEmployeeName() + "\nEmployeeId : " + employee.getEmployeeId() + "\nEmployee Date of birth : " + 
   employee.getDob() + "\nEmployee Date of join : " + employee.getDoj() + "\nEmployee Departmentname: " + employee.getdepartment().getDepartmentName();
	   }
	   else {
		   throw new NoSuchElementException();
	   }
}
   
  @RequestMapping("/employees/salary_information/{name}/{id}/{id}")
   public String getEmployee2(@PathVariable String name,@PathVariable String id){
	   Employee employee = employeeService.getEmployee(name);
	   Attendance attendance=attendanceService.getAttendance(id);
	   Salary salary= salaryService.getSalary(id);	   
	   Calendar c = Calendar.getInstance();
	      int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	      int totalsalary=(salary.getActualsalary()/monthMaxDays)*attendance.getWorking_days();
	      if(employee!=null && attendance!=null && salary!=null)
	      { 
	   return "\nEmployeeName : " + employee.getEmployeeName() + "\nEmployeeId : " + employee.getEmployeeId() + "\n Month : " + 
   attendance.getMonth() + "\nYear : " + attendance.getYear() + "\nActual salary : " + salary.getActualsalary()
   + "\n calculated salary : " + totalsalary;
	   
  }else {
	  throw new NoSuchElementException();
  }
  }	  
  
 
   
   
}