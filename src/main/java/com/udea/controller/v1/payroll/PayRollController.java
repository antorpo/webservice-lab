package com.udea.controller.v1.payroll;

import com.udea.model.base.ResponseAPI;
import com.udea.model.payroll.PayRoll;
import com.udea.model.payroll.PaymentConcept;
import com.udea.service.payroll.PayRollService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * PayRollController
 * @author Antonio
 */
@RestController
@RequestMapping("/api/v1/payrolls")
@CrossOrigin("*")
@Api(value="Payroll Reports")
public class PayRollController {
    @Autowired
    PayRollService payrollService;
   
    // PayRoll
    @ApiOperation(value = "View a list of available payment roll for each employee", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/")
    public List<PayRoll> index() {
        return payrollService.getAllPayRoll();
    }   
    
    @ApiOperation(value = "Get a payroll by employee id")
    @GetMapping("/{id}")
    public PayRoll getPayRollByEmployee(@ApiParam(value = "Employee id from which payroll object will retrieve", required = true)
            @PathVariable("id") long id) {
        return payrollService.getPayRollByEmployee(id);
    }
    
    @ApiOperation(value = "PayRoll bonus by employee id")
    @PutMapping("/bonus/{id}")
    public ResponseAPI payRollBonus(@ApiParam(value = "Employee id from which payroll object will retrieve", required = true)
            @PathVariable("id") long id) {
        ResponseAPI response = payrollService.setPayRollBonus(id);
        return response;
    }
    
    // Concepts 
    @ApiOperation(value = "View a list of available payment roll", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/concepts")
    public Iterable<PaymentConcept> listConcepts() {
        return payrollService.list();
    }
    
    @ApiOperation(value = "Add Payment Concept")
    @PostMapping("/concepts/add")
    public PaymentConcept addConcept(@ApiParam(value = "Concepts stored id DB", required = true) @RequestBody PaymentConcept concept) {
        return payrollService.save(concept);
    }

    @ApiOperation(value = "Get a concept by id")
    @GetMapping("/concepts/{id}")
    public PaymentConcept getConceptById(@ApiParam(value = "Concept id from which employee object will retrieve", required = true)
            @PathVariable("id") long id) {
        return payrollService.listId(id);
    }
    
    @ApiOperation(value = "Delete a concept by id")
    @DeleteMapping("/concepts/delete/{id}")
    public ResponseAPI deleteEmployee(@ApiParam(value = "Concept id to delete", required = true) @PathVariable(value = "id") Long id) {
        ResponseAPI response = payrollService.deleteById(id);
        return response;
    }
    
    @ApiOperation(value = "Delete a employee by id")
    @PutMapping("/concepts/update")
    public PaymentConcept updateConcept(@ApiParam(value = "Concept stored in DB", required = true) @RequestBody PaymentConcept employee) {
        return payrollService.update(employee);
    }
}
