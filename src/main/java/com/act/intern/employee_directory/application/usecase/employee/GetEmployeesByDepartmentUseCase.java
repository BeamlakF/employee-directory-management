package com.act.intern.employee_directory.application.usecase.employee;

import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetEmployeesByDepartmentUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    public GetEmployeesByDepartmentUseCase(EmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }

    public List<Employee> execute(Long departmentId) {
        return employeeRepositoryPort.findByDepartmentId(departmentId);
    }
}