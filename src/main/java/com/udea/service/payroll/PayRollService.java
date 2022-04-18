package com.udea.service.payroll;

import com.udea.dao.IEmployeeDAO;
import com.udea.dao.IPayRollDAO;
import com.udea.exception.ModelNotFoundException;
import com.udea.model.base.ResponseAPI;
import com.udea.model.constants.Constants;
import com.udea.model.employees.Employee;
import com.udea.model.enums.TypeConcept;
import com.udea.model.payroll.PayRoll;
import com.udea.model.payroll.PaymentConcept;
import com.udea.service.employees.EmployeeService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PayRollService
 * @author Antonio
 */
@Service
public class PayRollService {
    
    @Autowired
    private IPayRollDAO payDao;
    
    @Autowired
    private IEmployeeDAO employeeDao;
    
    @Autowired
    private EmployeeService employeeService;

    // PayRoll
    public List<PayRoll> getAllPayRoll(){
        List<PayRoll> listPay = new ArrayList<>();
        Iterable<Employee> list = employeeDao.findAll();
        
        for(Employee emp : list){
            listPay.add(getPayRollByEmployee(emp.getId()));
        }
        
        return listPay;
    }
    
    
    public PayRoll getPayRollByEmployee(long id){
        
        if(!employeeDao.existsById(id))
            throw new ModelNotFoundException(Constants.EMPLOYEE_NOT_EXISTS);
        
        Employee employee = employeeService.listId(id);
        List<PaymentConcept> list = payDao.findPayRollByEmployeed(id);
        PayRoll payRoll = new PayRoll();
        
        payRoll.setIdEmployee(id);
        payRoll.setBaseSalary(employee.getSalary());
        payRoll.setPaymentConcepts(list);
        
        double paySalary = employee.getSalary();
        
        for(PaymentConcept pc : list){
            
            if(pc.getType().toLowerCase()
                    .equals(TypeConcept.CREDITO.label)){
                paySalary -= pc.getValue();
            }
            
            if(pc.getType().toLowerCase()
                    .equals(TypeConcept.DEBITO.label)){
                paySalary += pc.getValue();
            }   
        }
        
        if(paySalary < 0)
            throw new ModelNotFoundException(Constants.NEGATIVE_SALARY);
        
         payRoll.setPaySalary(paySalary);
         
        return payRoll;
    }
    
    public ResponseAPI setPayRollBonus(long id){
        
        if(!employeeDao.existsById(id))
            throw new ModelNotFoundException(Constants.EMPLOYEE_NOT_EXISTS);
        
         Employee employee = employeeService.listId(id);

         if(!(getDifferenceDays(employee.getAdmissionDate(), new Date()) >= Constants.TWO_YEARS_IN_DAYS)){
             throw new ModelNotFoundException(Constants.NOT_REQUIRED_TIME);
         }
         
         employee.setSalary((1.1D * employee.getSalary()));
         
         employeeService.update(employee);
         
        ResponseAPI response = new ResponseAPI();
        response.setMessage(Constants.BONUS_MSG);
        
        return response;
    }
    
    // Concept
    public PaymentConcept save(PaymentConcept t) {
        
        if(!employeeDao.existsById(t.getIdEmployee()))
            throw new ModelNotFoundException(Constants.EMPLOYEE_NOT_EXISTS);
        
        return payDao.save(t);
    }

    public PaymentConcept update(PaymentConcept t) {
        
        if(!employeeDao.existsById(t.getIdEmployee()))
            throw new ModelNotFoundException(Constants.EMPLOYEE_NOT_EXISTS);
        
        return payDao.save(t);
    }

    public ResponseAPI delete(PaymentConcept t) {
        payDao.delete(t);
        
        ResponseAPI response = new ResponseAPI();
        response.setMessage(Constants.DELETE_MSG);
        
        return response;
    }
    
    public ResponseAPI deleteById(long id){
        payDao.deleteById(id);
        
        ResponseAPI response = new ResponseAPI();
        response.setMessage(Constants.DELETE_MSG);
        
        return response;
    }

    public Iterable<PaymentConcept> list() {
        return payDao.findAll();
    }

    public PaymentConcept listId(long id) {
        Optional<PaymentConcept> paymentConcept = payDao.findById(id);
        
        if (paymentConcept.isPresent()) {
            return paymentConcept.get();
        }

        throw new ModelNotFoundException(Constants.CONCEPT_NOT_FOUND);
    }
    
    // Private
    private long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
