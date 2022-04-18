package com.udea.service.employees;

import com.udea.dao.IEmployeeDAO;
import com.udea.exception.ModelNotFoundException;
import com.udea.model.base.ResponseAPI;
import com.udea.model.constants.Constants;
import com.udea.model.employees.Employee;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EmployeeService
 * @author Antonio
 */
@Service
public class EmployeeService {
    
    @Autowired
    private IEmployeeDAO employeeDao;

    public Employee save(Employee t) {
        return employeeDao.save(t);
    }

    public Employee update(Employee t) {
        return employeeDao.save(t);
    }

    public ResponseAPI delete(Employee t) {
        employeeDao.delete(t);
        ResponseAPI response = new ResponseAPI();
        response.setMessage(Constants.DELETE_MSG);
        
        return response;
    }
    
    public ResponseAPI deleteById(long id){
        employeeDao.deleteById(id);
        
        ResponseAPI response = new ResponseAPI();
        response.setMessage(Constants.DELETE_MSG);
        
        return response;
    }

    public Iterable<Employee> list() {
        return employeeDao.findAll();
    }

    public Employee listId(long id) {
        Optional<Employee> employee = employeeDao.findById(id);
        
        if (employee.isPresent()) {
            return employee.get();
        }

        throw new ModelNotFoundException(Constants.EMPLOYEE_NOT_EXISTS);
    }
}
