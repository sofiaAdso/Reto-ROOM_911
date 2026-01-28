package com.room911.Business;

import com.room911.entity.Employees;
import com.room911.utils.exception.BusinessException;
import com.room911.utils.exception.ResourceNoFoundException;
import com.room911.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeBusiness {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employees create(Employees employee) {

        if (employee.getIdentification() == null || employee.getIdentification().trim().isEmpty()) {
            throw new BusinessException("Employee identification is required");
        }

        Optional<Employees> existingEmployee =
                employeeRepository.findByIdentification(employee.getIdentification());

        if (existingEmployee.isPresent()) {
            throw new BusinessException(
                    "An employee with this identification already exists: "
                            + employee.getIdentification());
        }

        if (employee.getFristName() == null || employee.getFristName().trim().isEmpty()) {
            throw new BusinessException("Employee first name is required");
        }

        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new BusinessException("Employee last name is required");
        }

        if (employee.getActive() == null) {
            employee.setActive(true);
        }

        return employeeRepository.save(employee);
    }

    public Employees update(Long id, Employees employee) {

        Employees existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNoFoundException("Employee not found with id: " + id));

        if (employee.getEmployeeId() != null) {
            existingEmployee.setEmployeeId(employee.getEmployeeId());
        }

        if (employee.getIdentification() != null && !employee.getIdentification().trim().isEmpty()) {

            Optional<Employees> duplicateEmployee =
                    employeeRepository.findByIdentification(employee.getIdentification());

            if (duplicateEmployee.isPresent()
                    && !duplicateEmployee.get().getEmployeeId().equals(existingEmployee.getEmployeeId())) {
                throw new BusinessException(
                        "Another employee already exists with identification: "
                                + employee.getIdentification());
            }

            existingEmployee.setIdentification(employee.getIdentification());
        }

        if (employee.getFristName() != null && !employee.getFristName().trim().isEmpty()) {
            existingEmployee.setFristName(employee.getFristName());
        }

        if (employee.getLastName() != null && !employee.getLastName().trim().isEmpty()) {
            existingEmployee.setLastName(employee.getLastName());
        }

        if (employee.getDepartmentId() != null) {
            existingEmployee.setDepartmentId(employee.getDepartmentId());
        }

        if (employee.getActive() != null) {
            existingEmployee.setActive(employee.getActive());
        }

        return employeeRepository.save(existingEmployee);
    }

    public Employees getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNoFoundException("Employee not found with id: " + id));
    }

    public Employees getByIdentification(String identification) {
        return employeeRepository.findByIdentification(identification)
                .orElseThrow(() ->
                        new ResourceNoFoundException(
                                "Employee not found with identification: " + identification));
    }

    public List<Employees> getAll() {
        return employeeRepository.findAll();
    }

    public List<Employees> getByDepartment(Long departmentId) {

        if (departmentId == null) {
            throw new BusinessException("Department ID is required");
        }

        return employeeRepository.findByDepartmentId(departmentId);
    }

    public void deactivate(Long id) {

        Employees employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNoFoundException("Employee not found with id: " + id));

        if (Boolean.FALSE.equals(employee.getActive())) {
            throw new BusinessException("Employee is already deactivated");
        }

        employee.setActive(false);
        employeeRepository.save(employee);
    }

    public void activate(Long id) {

        Employees employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNoFoundException("Employee not found with id: " + id));

        if (Boolean.TRUE.equals(employee.getActive())) {
            throw new BusinessException("Employee is already active");
        }

        employee.setActive(true);
        employeeRepository.save(employee);
    }

    public void delete(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new ResourceNoFoundException("Employee not found with id: " + id);
        }

        employeeRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return employeeRepository.existsById(id);
    }

    public boolean existsByIdentification(String identification) {
        return employeeRepository.findByIdentification(identification).isPresent();
    }

    public long countEmployees() {
        return employeeRepository.count();
    }
}
