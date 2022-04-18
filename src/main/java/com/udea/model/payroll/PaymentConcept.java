package com.udea.model.payroll;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * PaymentConcept
 * @author Antonio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "All details about the payment concept")
@Entity
public class PaymentConcept implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated payment concept id")
    private Long Id;
    
    @Column(name = "idemployee", nullable = false)
    @ApiModelProperty(notes = "The employee id")
    private Long idEmployee;
    
    @Column(name = "type", nullable = false)
    @ApiModelProperty(notes = "The type of payment concept")
    private String type;
    
    @Column(name = "name", nullable = false)
    @ApiModelProperty(notes = "The name of payment concept")
    private String name;
    
    @Column(name = "value", nullable = false)
    @ApiModelProperty(notes = "The value of payment concept")
    private double value;
}
