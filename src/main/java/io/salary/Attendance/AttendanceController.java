package io.salary.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.salary.Employee.Employee;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(method= RequestMethod.POST , value = "/attendance")
    public void addAttendance(@ModelAttribute Attendance attendance){
        attendanceService.addAttendance(attendance);
    }
    @RequestMapping(method=RequestMethod.DELETE,value ="/attendance/{id}")
	public void deleteAttendance(@PathVariable String id){
	attendanceService.deletebyId(id);
	}
    @RequestMapping(method=RequestMethod.PUT,value ="/attendance/{id}")
	public void updateTopic(@RequestBody Attendance attendance,@PathVariable String id) {			
    	attendanceService.updateAttendance(id,attendance);
    	
	}
     @RequestMapping("/attendance")
    public List<Attendance> getAllAttendances(){
       return attendanceService.getAllAttendances();
    }
   
     @RequestMapping("/attendance/{id}")
 	public ResponseEntity<Object> getAttendance(@PathVariable String  id) {
  		Attendance attendance =attendanceService.getAttendance(id);
 		if(attendance!=null)
 		{
 			return new ResponseEntity<Object>(attendance,HttpStatus.CREATED);
 		}
 		else
 		{
 			throw new NoSuchElementException();
 		}
 	}
     
}