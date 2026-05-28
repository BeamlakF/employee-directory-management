package com.act.intern.employee_directory.application.usecase.employee;

import com.act.intern.employee_directory.domain.exception.DuplicateResourceException;
import com.act.intern.employee_directory.domain.exception.ResourceNotFoundException;
import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;
    private final DepartmentRepositoryPort departmentRepositoryPort;

    public CreateEmployeeUseCase(
            EmployeeRepositoryPort employeeRepositoryPort,
            DepartmentRepositoryPort departmentRepositoryPort
    ) {
        this.employeeRepositoryPort = employeeRepositoryPort;
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    public Employee execute(Employee employee, Long departmentId) {

        if (employeeRepositoryPort.existsByEmail(employee.getEmail())) {
            throw new DuplicateResourceException("Employee email already exists");
        }

        Department department = departmentRepositoryPort.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        employee.setDepartment(department);

        return employeeRepositoryPort.save(employee);
    }
}