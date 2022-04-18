package com.udea.controller.v1.employees;

import com.udea.model.base.ResponseAPI;
import com.udea.model.employees.Employee;
import com.udea.service.employees.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController
 * @author Antonio
 */
@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin("*")
@Api(value="Employee Management System")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
   
    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/")
    public Iterable<Employee> index() {
        return employeeService.list();
    }
    
    @ApiOperation(value = "Add Employee")
    @PostMapping("/add")
    public Employee addEmployee(@ApiParam(value = "Employee stored id DB", required = true) @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @ApiOperation(value = "Get a employee by id")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true)
            @PathVariable("id") long id) {
        return employeeService.listId(id);
    }
    
    @ApiOperation(value = "Delete a employee by id")
    @DeleteMapping("/delete/{id}")
    public ResponseAPI deleteEmployee(@ApiParam(value = "Employee id to delete", required = true) @PathVariable(value = "id") Long id) {
        ResponseAPI response = employeeService.deleteById(id);
        return response;
    }
    
    @ApiOperation(value = "Delete a employee by id")
    @PutMapping("/update")
    public Employee updateEmployee(@ApiParam(value = "Employee stored in DB", required = true) @RequestBody Employee employee) {
        return employeeService.update(employee);
    }
}
