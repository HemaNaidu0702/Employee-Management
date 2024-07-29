package com.javafullstack.ems.mapper;

import com.javafullstack.ems.dto.DepartmentDto;
import com.javafullstack.ems.entity.Department;

public class DepartmentMapper {

//    to convert department jpa entity into department dto

    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    //    to convert departmentdto into department jpa entity
    public static Department mapToDepartment(DepartmentDto departmentdto){
        return new Department(
               departmentdto.getId(),
                departmentdto.getDepartmentName(),
                departmentdto.getDepartmentDescription()
        );
    }

}
