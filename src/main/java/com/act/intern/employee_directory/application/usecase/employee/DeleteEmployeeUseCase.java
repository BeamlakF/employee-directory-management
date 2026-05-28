package com.act.intern.employee_directory.application.usecase.employee;

import com.act.intern.employee_directory.domain.exception.ResourceNotFoundException;
import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    public DeleteEmployeeUseCase(EmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }

    public void execute(Long id) {

        Employee employee = employeeRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employeeRepositoryPort.delete(employee);
    }
}