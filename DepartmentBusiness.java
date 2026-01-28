package com.room911.Business;

import com.room911.entity.Department;
import com.room911.utils.exception.BusinessException;
import com.room911.utils.exception.ResourceNoFoundException;
import com.room911.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentBusiness {

    @Autowired
    private DepartmentRepository departmentRepository;


    public Department create(Department department) {

        if (department.getDepartmentName() == null || department.getDepartmentName().trim().isEmpty()) {
            throw new BusinessException("Department name is required");
        }

        return departmentRepository.save(department);
    }

    public Department update(Long id, Department department) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("Department not found with id: " + id));

        if (department.getDepartmentId() != null) {
            existingDepartment.setDepartmentId(department.getDepartmentId());
        }

        if (department.getDepartmentName() != null && !department.getDepartmentName().trim().isEmpty()) {
            existingDepartment.setDepartmentName(department.getDepartmentName());
        }

        return departmentRepository.save(existingDepartment);
    }


    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("Department not found with id: " + id));
    }


    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNoFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }


    public boolean exists(Long id) {
        return departmentRepository.existsById(id);
    }

    public long countDepartments() {
        return departmentRepository.count();
    }
}
