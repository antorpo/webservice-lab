package com.udea.dao;

import com.udea.model.payroll.PayRoll;
import com.udea.model.payroll.PaymentConcept;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * IPayRollDAO
 * @author Antonio
 */
public interface IPayRollDAO extends JpaRepository<PaymentConcept, Long> {
    
    @Query("SELECT p FROM PaymentConcept p WHERE p.idEmployee = :id")
    public List<PaymentConcept> findPayRollByEmployeed(@Param("id") Long id);
}
