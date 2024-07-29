package com.javafullstack.ems.service;

import com.javafullstack.ems.dto.EmployeeDto;
import com.javafullstack.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createemployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId,EmployeeDto updatedemployee);

    void deleteemployee(Long employeeId);
}
