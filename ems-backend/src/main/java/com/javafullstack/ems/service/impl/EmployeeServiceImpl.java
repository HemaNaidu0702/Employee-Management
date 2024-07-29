package com.javafullstack.ems.service.impl;

import com.javafullstack.ems.dto.EmployeeDto;
import com.javafullstack.ems.entity.Department;
import com.javafullstack.ems.entity.Employee;
import com.javafullstack.ems.exception.ResourceNotFoundException;
import com.javafullstack.ems.mapper.EmployeeMapper;
import com.javafullstack.ems.repository.DepartmentRepository;
import com.javafullstack.ems.repository.EmployeeRepository;
import com.javafullstack.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createemployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department doesnt exist" + employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedemployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedemployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist for the given Id " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
//        it streams every single object in the list and the accordingly maps to dto parameters
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedemployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee doesn't exist for the id " + employeeId)
        );

        employee.setFirstname(updatedemployee.getFirstname());
        employee.setLastname(updatedemployee.getLastname());
        employee.setEmail(updatedemployee.getEmail());

        Department department = departmentRepository.findById(updatedemployee.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department doesnt exist" + updatedemployee.getDepartmentId()));
        employee.setDepartment(department);
        Employee Updatedempobj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(Updatedempobj);
    }

    @Override
    public void deleteemployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee doesn't exist for the id " + employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }
}
