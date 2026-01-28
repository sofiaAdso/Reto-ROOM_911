package com.room911.service;

import com.room911.entity.Employees;
import com.room911.repository.EmployeeRepository;
import com.room911.service.DAO.IDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IDAO<Employees, Long> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employees save(Employees employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employees> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employees> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void delete(Employees entity) {
        employeeRepository.delete(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public long count() {
        return employeeRepository.count();
    }

    // Métodos adicionales específicos de Employee
    public Optional<Employees> findByIdentification(String identification) {
        return employeeRepository.findByIdentification(identification);
    }

    public List<Employees> findByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
