package com.act.intern.employee_directory.application.usecase.employee;

import com.act.intern.employee_directory.domain.exception.ResourceNotFoundException;
import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeByIdUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    public GetEmployeeByIdUseCase(EmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }

    public Employee execute(Long id) {

        return employeeRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }
}