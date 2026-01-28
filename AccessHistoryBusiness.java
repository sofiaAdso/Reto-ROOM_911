package com.room911.Business;

import com.room911.entity.AccessHistory;
import com.room911.entity.Employees;
import com.room911.utils.exception.BusinessException;
import com.room911.utils.exception.ResourceNoFoundException;
import com.room911.repository.AccessHistoryRepository;
import com.room911.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AccessHistoryBusiness {

    @Autowired
    private AccessHistoryRepository accessHistoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Registrar un acceso basado en la identificación del empleado
     */
    public AccessHistory registerAccess(String identification) {

        if (identification == null || identification.trim().isEmpty()) {
            throw new BusinessException("Identification is required");
        }

        AccessHistory history = new AccessHistory();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        history.setAccessDate(now.format(dateFormatter));
        history.setAccessTime(now.format(timeFormatter));

        Optional<Employees> employeeOpt = employeeRepository.findByIdentification(identification);

        if (employeeOpt.isPresent()) {
            Employees employee = employeeOpt.get();
            history.setEmployeeId(employee.getEmployeeId());

            if (Boolean.TRUE.equals(employee.getActive())) {
                history.setResult("ACCESS_GRANTED");
            } else {
                history.setResult("ACCESS_DENIED - Employee inactive");
            }
        } else {
            history.setResult("ACCESS_DENIED - Employee not found");
        }

        return accessHistoryRepository.save(history);
    }

    /**
     * Obtener historial de accesos por ID de empleado
     */
    public List<AccessHistory> getByEmployeeId(Long employeeId) {

        if (employeeId == null) {
            throw new BusinessException("Employee ID is required");
        }

        return accessHistoryRepository.findByEmployeeId(employeeId);
    }

    /**
     * Obtener historial de acceso por ID
     */
    public AccessHistory getById(Long id) {
        return accessHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("Access history not found with id: " + id));
    }

    /**
     * Listar todo el historial de accesos
     */
    public List<AccessHistory> getAll() {
        return accessHistoryRepository.findAll();
    }

    /**
     * Eliminar un registro de historial
     */
    public void delete(Long id) {
        if (!accessHistoryRepository.existsById(id)) {
            throw new ResourceNoFoundException("Access history not found with id: " + id);
        }
        accessHistoryRepository.deleteById(id);
    }

    /**
     * Contar total de registros de acceso
     */
    public long countAccessRecords() {
        return accessHistoryRepository.count();
    }
}
