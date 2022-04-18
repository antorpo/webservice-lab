package com.udea.model.employees;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Employee
 * @author Antonio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "All details about the employee")
@Entity
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated employee id")
    private Long Id;
    
    @Column(name = "firstname", nullable = false, length = 80)
    @ApiModelProperty(notes = "The first name")
    private @NonNull String firstName;
    
    @Column(name = "lastname", nullable = false, length = 80)
    @ApiModelProperty(notes = "The last name")
    private @NonNull String lastName;

    @Column(name = "email", nullable = false, length = 80)
    @ApiModelProperty(notes = "The employee email")
    private @NonNull String email;
    
    @Column(name = "salary", nullable = false)
    @ApiModelProperty(notes = "The employee salary")
    private double salary;
    
    @Column(name = "position", nullable = false, length = 80)
    @ApiModelProperty(notes = "The employee position")
    private @NonNull String position;
    
    @Column(name = "dependency", nullable = false, length = 80)
    @ApiModelProperty(notes = "The employee dependency")
    private @NonNull String dependency;
 
    @Column(name = "admissionDate", nullable = false, length = 80)
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "The employee admission date")
    private @NonNull Date admissionDate;
}
