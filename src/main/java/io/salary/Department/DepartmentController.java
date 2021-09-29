package io.salary.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.salary.Employee.Employee;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method= RequestMethod.POST , value = "/departments")
    public Department addDepartment(@ModelAttribute Department department){
        return departmentService.addDepartment(department);
    }
   @RequestMapping(method=RequestMethod.DELETE,value ="/departments/{name}")
	public void deleteDepartment(@PathVariable String name){
	departmentService.deletebyName(name);
	}
       
    @RequestMapping(method=RequestMethod.PUT,value ="/departments/{name}")
	public void updateTopic(@RequestBody Department department,@PathVariable String name) {			
    	departmentService.updateTopic(name,department);
    	
	}
     @RequestMapping("/departments")
    public List<Department> getAllDepartments(){
       return departmentService.getAllDepartments();
    }
   
    @RequestMapping("/departments/{name}")
   	public ResponseEntity<Object> getDepartment(@PathVariable String  name) {
    		Department department =departmentService.getDepartment(name);
   		if(department!=null)
   		{
   			return new ResponseEntity<Object>(department,HttpStatus.CREATED);
   		}
   		else
   		{
   			throw new NoSuchElementException();
   		}
   	}
    
    
}