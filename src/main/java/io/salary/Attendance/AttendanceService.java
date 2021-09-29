package io.salary.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendances() {
        List<Attendance> attendances = new LinkedList();
        attendanceRepository.findAll().forEach(attendances::add);
        return attendances;
    }

    public Attendance getAttendance(String id) {
        return attendanceRepository.findByEmployeeId(id);
    }

	public Attendance deletebyId(String id) {
		//departmentRepository.deleteById(name);
		Attendance att=attendanceRepository.findByEmployeeId(id);
		attendanceRepository.delete(att);
		return att;	
	}
	public void updateAttendance(String name, Attendance attendance) {
		attendanceRepository.save(attendance);
			}

		
	}

	
