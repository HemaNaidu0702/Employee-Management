package com.javafullstack.ems.mapper;

import com.javafullstack.ems.dto.EmployeeDto;
import com.javafullstack.ems.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                employee.getDepartment().getId()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {

                Employee employee = new Employee();
                employee.setId(employeeDto.getId());
                employee.setFirstname(employeeDto.getFirstname());
                employee.setLastname(employeeDto.getLastname());
                employee.setEmail(employeeDto.getEmail());
                return employee;
    }
}
