package com.udea.model.payroll;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/***
 *  PayRoll
 * @author Antonio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "All details about the payment roll for each employee")
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
