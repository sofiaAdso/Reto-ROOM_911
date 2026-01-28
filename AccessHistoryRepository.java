package com.room911.repository;

import com.room911.entity.AccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessHistoryRepository extends JpaRepository<AccessHistory, Long> {

    List<AccessHistory> findByEmployeeId(Long employeeId);
}
