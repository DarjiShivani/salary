package io.salary.Salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import io.salary.Attendance.*;
@Service
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    public Salary addSalary(Salary salary) {
        return  salaryRepository.save(salary);
    }

    public List<Salary> getAllSalary() {
        List<Salary> salaries = new LinkedList();
        salaryRepository.findAll().forEach(salaries::add);
        return salaries;
    }

    public Salary getSalary(String id) {
        return salaryRepository.findByEmployeeId(id);
    }

	public Salary deletebyId(String id) {
		//departmentRepository.deleteById(name);
		Salary sal=salaryRepository.findByEmployeeId(id);
		salaryRepository.delete(sal);
		return sal;	
	}
	public void updateSalary(String id, Salary salary) {
		salaryRepository.save(salary);
			}
	
}
	
