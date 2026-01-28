package com.room911.controller;

import com.room911.dto.request.RoleRequestDTO;
import com.room911.dto.response.RoleResponseDTO;
import com.room911.entity.Role;
import com.room911.Business.RoleBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class Rolecontroller {

    @Autowired
    private RoleBusiness roleBusiness;

    @PostMapping
    public RoleResponseDTO create(@Valid @RequestBody RoleRequestDTO dto) {
        Role role = new Role();
        role.setRoleName(dto.getRoleName());
        role.setDescription(dto.getDescription());
        return mapToResponse(roleBusiness.create(role));
    }

    @GetMapping
    public List<RoleResponseDTO> getAll() {
        return roleBusiness.getAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleResponseDTO getById(@PathVariable Long id) {
        Role role = roleBusiness.getById(id);
        return mapToResponse(role);
    }

    @PutMapping("/{id}")
    public RoleResponseDTO update(@PathVariable Long id, @Valid @RequestBody RoleRequestDTO dto) {
        Role role = new Role();
        role.setRoleName(dto.getRoleName());
        role.setDescription(dto.getDescription());
        return mapToResponse(roleBusiness.update(id, role));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleBusiness.delete(id);
    }

    @GetMapping("/name/{name}")
    public RoleResponseDTO getByName(@PathVariable String name) {
        Role role = roleBusiness.getByName(name);
        return mapToResponse(role);
    }

    private RoleResponseDTO mapToResponse(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());
        return dto;
    }
}
