package com.javafullstack.ems.controller;

import com.javafullstack.ems.dto.DepartmentDto;
import com.javafullstack.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createdepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto departmentadded = departmentService.createdepartment(departmentDto);
        return  new ResponseEntity<>(departmentadded, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getdepartmentById(@PathVariable("id") Long departmentId) {
         DepartmentDto departmentDto = departmentService.getDepartmenyById(departmentId);
         return ResponseEntity.ok(departmentDto);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departments =  departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId,
                                                          @RequestBody DepartmentDto departmentDto) {
       DepartmentDto updateddept = departmentService.updateDepartment(departmentId,departmentDto);
       return ResponseEntity.ok(updateddept);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Deprtment Deleted");
    }

}



