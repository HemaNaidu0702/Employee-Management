package com.javafullstack.ems.service.impl;

import com.javafullstack.ems.dto.DepartmentDto;
import com.javafullstack.ems.entity.Department;
import com.javafullstack.ems.mapper.DepartmentMapper;
import com.javafullstack.ems.repository.DepartmentRepository;
import com.javafullstack.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createdepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment =  departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmenyById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new RuntimeException("Department not found" + departmentId)
        );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto givenupdateDepartment) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new RuntimeException("Department not found" + departmentId)
        );

        department.setDepartmentName(givenupdateDepartment.getDepartmentName());
        department.setDepartmentDescription(givenupdateDepartment.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);

    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(
                ()-> new RuntimeException("Department not found" + departmentId)
        );
        departmentRepository.deleteById(departmentId);
    }
}
