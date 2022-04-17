package com.udea.model.payroll;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * PaymentConcept
 * @author Antonio
 */
public class PaymentConcept implements Serializable {
    
    @Column(name = "idemployee")
    @ApiModelProperty(notes = "The employee id")
    private Long idEmployee;
    
    @Column(name = "type")
    @ApiModelProperty(notes = "The type of payment concept")
    private String type;
    
    @Column(name = "name")
    @ApiModelProperty(notes = "The name of payment concept")
    private String name;
    
    @Column(name = "value")
    @ApiModelProperty(notes = "The value of payment concept")
    private double value;
}
