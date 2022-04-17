package com.udea.model.payroll;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/***
 *  PayRoll
 * @author Antonio
 */
public class PayRoll implements Serializable {

    @ApiModelProperty(notes = "The employee id")
    private Long idEmployee;

    @ApiModelProperty(notes = "The employee base salary")
    private double baseSalary;

    @ApiModelProperty(notes = "The employee pay salary")
    private double paySalary;

    @ApiModelProperty(notes = "The employee list of payment concepts")
    private List<PaymentConcept> paymentConcepts;

}
