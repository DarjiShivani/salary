package io.salary.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,String> {

    public Department findByDepartmentName(String departmentName);
}