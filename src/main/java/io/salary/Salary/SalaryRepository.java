package io.salary.Salary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary,String> {

    public Salary findByEmployeeId(String employeeId);
}