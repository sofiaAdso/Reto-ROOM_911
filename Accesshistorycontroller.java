package com.room911.controller;

import com.room911.dto.request.AccessHistoryRequestDTO;
import com.room911.dto.response.AccessHistoryResponseDTO;
import com.room911.entity.AccessHistory;
import com.room911.Business.AccessHistoryBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/access-history")
public class Accesshistorycontroller {

    @Autowired
    private AccessHistoryBusiness accessHistoryBusiness;

    @PostMapping
    public AccessHistoryResponseDTO register(@Valid @RequestBody AccessHistoryRequestDTO dto) {
        AccessHistory accessHistory = accessHistoryBusiness.registerAccess(dto.getIdentificationEntered());
        return mapToResponse(accessHistory);
    }

    @GetMapping("/{id}")
    public AccessHistoryResponseDTO getById(@PathVariable Long id) {
        return mapToResponse(accessHistoryBusiness.getById(id));
    }

    @GetMapping
    public List<AccessHistoryResponseDTO> getAll() {
        return accessHistoryBusiness.getAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<AccessHistoryResponseDTO> getByEmployee(@PathVariable Long employeeId) {
        return accessHistoryBusiness.getByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accessHistoryBusiness.delete(id);
    }

    private AccessHistoryResponseDTO mapToResponse(AccessHistory entity) {
        AccessHistoryResponseDTO dto = new AccessHistoryResponseDTO();
        dto.setId(entity.getId());
        dto.setHistoryId(entity.getHistoryId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setAccessDate(entity.getAccessDate());
        dto.setAccessTime(entity.getAccessTime());
        dto.setResult(entity.getResult());
        return dto;
    }
}
