package com.room911.Business;

import com.room911.entity.BulkUpload;
import com.room911.utils.exception.BusinessException;
import com.room911.utils.exception.ResourceNoFoundException;
import com.room911.repository.BulkUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BulkUploadBusiness {

    @Autowired
    private BulkUploadRepository bulkUploadRepository;

    /**
     * Registrar una nueva carga masiva
     */
    public BulkUpload registerUpload(Integer userId, String departmentId, String fileName, String totalRecords) {

        if (userId == null) {
            throw new BusinessException("User ID is required");
        }

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new BusinessException("File name is required");
        }

        BulkUpload upload = new BulkUpload();
        upload.setUserId(userId);
        upload.setDepartmentId(departmentId);
        upload.setFileName(fileName);
        upload.setTotalRecords(totalRecords);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        upload.setLoadDate(now.format(formatter));

        return bulkUploadRepository.save(upload);
    }

    public BulkUpload update(Long id, BulkUpload bulkUpload) {

        BulkUpload existing = bulkUploadRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("Bulk upload not found with id: " + id));

        if (bulkUpload.getLoadId() != null && !bulkUpload.getLoadId().trim().isEmpty()) {
            existing.setLoadId(bulkUpload.getLoadId());
        }

        if (bulkUpload.getUserId() != null) {
            existing.setUserId(bulkUpload.getUserId());
        }

        if (bulkUpload.getDepartmentId() != null) {
            existing.setDepartmentId(bulkUpload.getDepartmentId());
        }

        if (bulkUpload.getFileName() != null && !bulkUpload.getFileName().trim().isEmpty()) {
            existing.setFileName(bulkUpload.getFileName());
        }

        if (bulkUpload.getTotalRecords() != null) {
            existing.setTotalRecords(bulkUpload.getTotalRecords());
        }

        return bulkUploadRepository.save(existing);
    }

    public BulkUpload getById(Long id) {
        return bulkUploadRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("Bulk upload not found with id: " + id));
    }


    public List<BulkUpload> getAll() {
        return bulkUploadRepository.findAll();
    }

    public void delete(Long id) {

        if (!bulkUploadRepository.existsById(id)) {
            throw new ResourceNoFoundException("Bulk upload not found with id: " + id);
        }

        bulkUploadRepository.deleteById(id);
    }


    public boolean exists(Long id) {
        return bulkUploadRepository.existsById(id);
    }

    public long countUploads() {
        return bulkUploadRepository.count();
    }
}
