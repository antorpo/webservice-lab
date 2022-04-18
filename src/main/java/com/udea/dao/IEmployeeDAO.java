package com.udea.dao;

import com.udea.model.employees.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * IEmployeeDAO
 * @author Antonio
 */
public interface IEmployeeDAO extends CrudRepository<Employee, Long> {
}
