package com.javafullstack.ems.service;

import com.javafullstack.ems.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto createdepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmenyById(Long departmentId);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto updateDepartment(Long departmentId, DepartmentDto givenupdateDepartment);

    void deleteDepartment(Long departmentId);
}
