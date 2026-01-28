package com.room911.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@Entity
public class AccessHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "history_id")
    private String historyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "accessHistories", "users"})
    private Employees employee;
    @Column(name = "access_Date")
    private String accessDate;
    @Column(name = "access_time")
    private String accessTime;
    @Column(name = "result")
    private String result;
    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getHistoryId() {
        return historyId;
    }
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }
    public Employees getEmployee() {
        return employee;
    }
    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    public String getAccessDate() {
        return accessDate;
    }
    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }
    public String getAccessTime() {
        return accessTime;
    }
    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    // Métodos de compatibilidad para trabajar con IDs
    public Integer getEmployeeId() {
        return employee != null ? employee.getEmployeeId() : null;
    }
    public void setEmployeeId(Integer employeeId) {
        if (employeeId != null) {
            Employees emp = new Employees();
            emp.setEmployeeId(employeeId);
            this.employee = emp;
        } else {
            this.employee = null;
        }
    }
}
