package com.room911.repository;

import com.room911.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {

    Optional<Employees> findByIdentification(String identification);

    List<Employees> findByDepartmentId(Long departmentId);
}
