package io.salary.Salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.salary.Employee.Employee;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @RequestMapping(method= RequestMethod.POST , value = "/salary")
    public void addSalary(@ModelAttribute Salary salary){
        salaryService.addSalary(salary);
    }
    @RequestMapping(method=RequestMethod.DELETE,value ="/salary/{id}")
	public void deleteSalary(@PathVariable String id){
	salaryService.deletebyId(id);
	}
    @RequestMapping(method=RequestMethod.PUT,value ="/salary/{id}")
	public void updateSalary(@RequestBody Salary salary,@PathVariable String id) {			
    	salaryService.updateSalary(id,salary);
    	
	}
     @RequestMapping("/salary")
    public List<Salary> getAllSalary(){
       return salaryService.getAllSalary();
    }

    @RequestMapping("/salary/{id}")
   	public ResponseEntity<Object> getSalary(@PathVariable String  id) {
    		Salary salary =salaryService.getSalary(id);
   		if(salary!=null)
   		{
   			return new ResponseEntity<Object>(salary,HttpStatus.CREATED);
   		}
   		else
   		{
   			throw new NoSuchElementException();
   		}
   	}
    
}
