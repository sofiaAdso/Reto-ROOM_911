package com.room911.controller;

import com.room911.dto.request.BulkUploadRequestDTO;
import com.room911.dto.response.BulkUploadResponseDTO;
import com.room911.entity.BulkUpload;
import com.room911.Business.BulkUploadBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bulk-uploads")
public class BulkUploadcontroller {

    @Autowired
    private BulkUploadBusiness bulkUploadBusiness;

    @PostMapping
    public BulkUploadResponseDTO create(@Valid @RequestBody BulkUploadRequestDTO dto) {
        BulkUpload upload = bulkUploadBusiness.registerUpload(
                dto.getUserId(),
                dto.getDepartmentId(),
                dto.getFileName(),
                dto.getTotalRecords()
        );
        return mapToResponse(upload);
    }

    @GetMapping
    public List<BulkUploadResponseDTO> getAll() {
        return bulkUploadBusiness.getAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BulkUploadResponseDTO getById(@PathVariable Long id) {
        BulkUpload upload = bulkUploadBusiness.getById(id);
        return mapToResponse(upload);
    }

    @PutMapping("/{id}")
    public BulkUploadResponseDTO update(@PathVariable Long id, @Valid @RequestBody BulkUploadRequestDTO dto) {
        BulkUpload upload = new BulkUpload();
        upload.setUserId(dto.getUserId());
        upload.setDepartmentId(dto.getDepartmentId());
        upload.setFileName(dto.getFileName());
        upload.setTotalRecords(dto.getTotalRecords());
        return mapToResponse(bulkUploadBusiness.update(id, upload));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bulkUploadBusiness.delete(id);
    }

    private BulkUploadResponseDTO mapToResponse(BulkUpload upload) {
        BulkUploadResponseDTO dto = new BulkUploadResponseDTO();
        dto.setId(upload.getId());
        dto.setUserId(upload.getUserId());
        dto.setDepartmentId(upload.getDepartmentId());
        dto.setFileName(upload.getFileName());

        // convertir totalRecords (String en la entidad) a Integer en el DTO si es posible
        String total = upload.getTotalRecords();
        if (total != null) {
            try {
                dto.setTotalRecords(Integer.valueOf(total));
            } catch (NumberFormatException e) {
                dto.setTotalRecords(null);
            }
        } else {
            dto.setTotalRecords(null);
        }

        dto.setLoadDate(upload.getLoadDate());
        return dto;
    }
}
