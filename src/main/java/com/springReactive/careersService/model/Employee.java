package com.springReactive.careersService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int empId;
    private String empName;
    private String empCity;
    private String empPhone;
    private double javaExp;
    private double springExp;

}
