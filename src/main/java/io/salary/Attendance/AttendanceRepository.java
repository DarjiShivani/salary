package io.salary.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AttendanceRepository extends JpaRepository<Attendance,String> {

    public Attendance findByEmployeeId(String employeeId);
}
