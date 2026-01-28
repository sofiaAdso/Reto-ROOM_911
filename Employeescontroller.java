package com.room911.controller;

import com.room911.dto.request.EmployeeRequestDTO;
import com.room911.dto.response.EmployeeResponseDTO;
import com.room911.entity.Employees;
import com.room911.Business.EmployeeBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class Employeescontroller {

    @Autowired
    private EmployeeBusiness employeeBusiness;

    @PostMapping
    public EmployeeResponseDTO create(@Valid @RequestBody EmployeeRequestDTO dto) {
        Employees employee = new Employees();
        employee.setIdentification(dto.getIdentification());
        employee.setFristName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        if (dto.getDepartmentId() != null) {
            employee.setDepartmentId(dto.getDepartmentId());
        }

        return mapToResponse(employeeBusiness.create(employee));
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getById(@PathVariable Long id) {
        Employees emp = employeeBusiness.getById(id);
        return mapToResponse(emp);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAll() {
        return employeeBusiness.getAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO dto) {
        Employees employee = new Employees();
        employee.setEmployeeId(null); // no viene en el request DTO
        employee.setIdentification(dto.getIdentification());
        employee.setFristName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        if (dto.getDepartmentId() != null) {
            employee.setDepartmentId(dto.getDepartmentId());
        }

        return mapToResponse(employeeBusiness.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeBusiness.delete(id);
    }

    @PostMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        employeeBusiness.deactivate(id);
    }

    @PostMapping("/{id}/activate")
    public void activate(@PathVariable Long id) {
        employeeBusiness.activate(id);
    }

    @GetMapping("/identification/{identification}")
    public EmployeeResponseDTO getByIdentification(@PathVariable String identification) {
        Employees emp = employeeBusiness.getByIdentification(identification);
        return mapToResponse(emp);
    }

    @GetMapping("/department/{departmentId}")
    public List<EmployeeResponseDTO> getByDepartment(@PathVariable Long departmentId) {
        return employeeBusiness.getByDepartment(departmentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponseDTO mapToResponse(Employees employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getEmployeeId().longValue());
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setIdentification(employee.getIdentification());
        dto.setFirstName(employee.getFristName());
        dto.setLastName(employee.getLastName());
        dto.setActive(employee.getActive());

        return dto;
    }
}
