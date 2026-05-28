package com.act.intern.employee_directory.application.usecase.employee;

import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllEmployeesUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    public GetAllEmployeesUseCase(EmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }

    public List<Employee> execute() {
        return employeeRepositoryPort.findAll();
    }
}