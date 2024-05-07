package com.redis.mapper;

import com.redis.dto.EmployeeDto;
import com.redis.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoMapper {

//    public EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto) {
//        return EmployeeEntity.builder()
//                .employeeName(employeeDto.getEmployeeName())
//                .zip(employeeDto.getZip())
//                .empDesignation(employeeDto.getEmpDesignation())
//                .empPan(employeeDto.getEmpPan())
//                .empAadhar(employeeDto.getEmpAadhar())
//                .empFirstName(employeeDto.getEmpFirstName())
//                .empLastName(employeeDto.getEmpLastName())
//                .empEmail(employeeDto.getEmpEmail())
//                .build();
//    }
    public EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto) {
        return EmployeeEntity.builder()
                .employeeName(employeeDto.getEmployeeName())
                .zip(employeeDto.getZip())
                .empDesignation(employeeDto.getEmpDesignation())
                .empPan(employeeDto.getEmpPan())
                .empAadhar(employeeDto.getEmpAadhar())
                .empFirstName(employeeDto.getEmpFirstName())
                .empLastName(employeeDto.getEmpLastName())
                .empEmail(employeeDto.getEmpEmail())
                .build();
    }



    public EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(employeeEntity, employeeDto);
        return employeeDto;
    }

}
