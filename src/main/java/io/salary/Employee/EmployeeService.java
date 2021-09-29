	package io.salary.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.salary.Attendance.Attendance;
import io.salary.Salary.Salary;

//import io.salary.Department.Department;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new LinkedList();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public Employee getEmployee(String name) {
        return employeeRepository.findByEmployeeName(name);
    }
    public void save(Employee employee) {
		employeeRepository.save(employee);		
	}
	public Employee deletebyName(String name) {
		Employee emp=employeeRepository.findByEmployeeName(name);
		employeeRepository.delete(emp);
		return emp;	
	}
	public void updateEmployee(String name, Employee employee) {
		//employeeRepository.save(name,employee);
		employeeRepository.save(employee);
}

	public Attendance getAttendanceById(String id) {
		return employeeRepository.findByAttendanceEmployeeId(id);
	}
	
	public Salary getSalaryById(String id) {
		return employeeRepository.findBySalaryId(id);
	}
	

	
}	
