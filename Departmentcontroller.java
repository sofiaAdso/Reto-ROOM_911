package com.room911.controller;

import com.room911.dto.request.DepartmentRequestDTO;
import com.room911.dto.response.DepartmentResponseDTO;
import com.room911.entity.Department;
import com.room911.Business.DepartmentBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class Departmentcontroller {

    @Autowired
    private DepartmentBusiness departmentBusiness;

    @PostMapping
    public DepartmentResponseDTO create(@Valid @RequestBody DepartmentRequestDTO dto) {
        Department department = new Department();
        department.setDepartmentName(dto.getName());
        return mapToResponse(departmentBusiness.create(department));
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAll() {
        return departmentBusiness.getAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getById(@PathVariable Long id) {
        Department dept = departmentBusiness.getById(id);
        return mapToResponse(dept);
    }

    @PutMapping("/{id}")
    public DepartmentResponseDTO update(@PathVariable Long id, @Valid @RequestBody DepartmentRequestDTO dto) {
        Department department = new Department();
        department.setDepartmentName(dto.getName());
        return mapToResponse(departmentBusiness.update(id, department));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentBusiness.delete(id);
    }

    private DepartmentResponseDTO mapToResponse(Department department) {
        DepartmentResponseDTO dto = new DepartmentResponseDTO();
        dto.setId(department.getDepartmentId());
        dto.setName(department.getDepartmentName());
        return dto;
    }
}
