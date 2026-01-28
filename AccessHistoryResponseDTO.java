package com.room911.dto.response;

public class AccessHistoryResponseDTO {

    private Long id;
    private String historyId;
    private Integer employeeId;
    private String accessDate;
    private String accessTime;
    private String result;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getHistoryId() { return historyId; }
    public void setHistoryId(String historyId) { this.historyId = historyId; }

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public String getAccessDate() { return accessDate; }
    public void setAccessDate(String accessDate) { this.accessDate = accessDate; }

    public String getAccessTime() { return accessTime; }
    public void setAccessTime(String accessTime) { this.accessTime = accessTime; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
}
