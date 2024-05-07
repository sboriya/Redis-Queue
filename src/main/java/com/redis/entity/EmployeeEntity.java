package com.redis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "EMPLOYEE_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ID")
    private Long id;
    @Column(name="EMP_NAME")
    private String employeeName;
    @Column(name="EMP_EMAIL")
    private String empEmail;
//    @Column(name="EMP_DOB")
//    private LocalDate empDob;
    @Column(name="EMP_PAN_NUMBER")
    private String empPan;
    @Column(name="EMP_AADHAR_NUMBER")
    private String empAadhar;

    @Column(name="EMP_DESIGNATION")
    private String empDesignation;
    @Column(name="EMP_FIRST_NAME")
    private String empFirstName;
    @Column(name="EMP_LAST_NAME")
    private String empLastName;
    @Column(name="ZIP")
    private String zip;
}
