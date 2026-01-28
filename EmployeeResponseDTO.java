package com.room911.dto.response;

public class EmployeeResponseDTO {

    private Long id;
    private Integer employeeId;
    private String identification;
    private String firstName;
    private String lastName;
    private Boolean active;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public String getIdentification() { return identification; }
    public void setIdentification(String identification) { this.identification = identification; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
