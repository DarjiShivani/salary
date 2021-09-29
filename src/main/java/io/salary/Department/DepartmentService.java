package io.salary.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new LinkedList();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    public Department getDepartment(String name) {
        return departmentRepository.findByDepartmentName(name);
    }
    
	public Department deletebyName(String name) {
		//departmentRepository.deleteById(name);
		Department dept=departmentRepository.findByDepartmentName(name);
		departmentRepository.delete(dept);
		return dept;	
	}
	public void updateTopic(String name, Department department) {
		departmentRepository.save(department);
			}

		
	}

	
