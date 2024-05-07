package com.redis.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeDto {
    private Long id;
    private String employeeName;
    private String empEmail;
//    private LocalDate empDob;
    private String empPan;
    private String empAadhar;
    private String empDesignation;
    private String empFirstName;
    private String empLastName;
    private String zip;
}
