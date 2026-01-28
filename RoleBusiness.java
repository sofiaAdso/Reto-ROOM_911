package com.room911.Business;

import com.room911.entity.Role;
import com.room911.utils.exception.BusinessException;
import com.room911.utils.exception.ResourceNoFoundException;
import com.room911.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleBusiness {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Crear un nuevo rol con validaciones de negocio
     */
    public Role create(Role role) {

        if (role.getRoleName() == null || role.getRoleName().trim().isEmpty()) {
            throw new BusinessException("Role name is required");
        }

        Optional<Role> existingRole = roleRepository.findByRoleName(role.getRoleName());
        if (existingRole.isPresent()) {
            throw new BusinessException("A role with this name already exists: " + role.getRoleName());
        }

        return roleRepository.save(role);
    }

    /**
     * Actualizar un rol existente
     */
    public Role update(Long id, Role role) {

        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("Role not found with id: " + id));

        if (role.getRoleId() != null && !role.getRoleId().trim().isEmpty()) {
            existingRole.setRoleId(role.getRoleId());
        }

        if (role.getRoleName() != null && !role.getRoleName().trim().isEmpty()) {
            Optional<Role> duplicateRole = roleRepository.findByRoleName(role.getRoleName());
            if (duplicateRole.isPresent() && !duplicateRole.get().getId().equals(id)) {
                throw new BusinessException("Another role already exists with name: " + role.getRoleName());
            }
            existingRole.setRoleName(role.getRoleName());
        }

        if (role.getDescription() != null) {
            existingRole.setDescription(role.getDescription());
        }

        return roleRepository.save(existingRole);
    }


    public Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("Role not found with id: " + id));
    }


    public Role getByName(String name) {
        return roleRepository.findByRoleName(name)
                .orElseThrow(() -> new ResourceNoFoundException("Role not found with name: " + name));
    }


    public List<Role> getAll() {
        return roleRepository.findAll();
    }


    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNoFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }


    public boolean exists(Long id) {
        return roleRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return roleRepository.findByRoleName(name).isPresent();
    }


    public long countRoles() {
        return roleRepository.count();
    }
}
