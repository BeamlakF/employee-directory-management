package com.act.intern.employee_directory.application.usecase.employee;

import com.act.intern.employee_directory.domain.exception.ResourceNotFoundException;
import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;
    private final DepartmentRepositoryPort departmentRepositoryPort;

    public UpdateEmployeeUseCase(
            EmployeeRepositoryPort employeeRepositoryPort,
            DepartmentRepositoryPort departmentRepositoryPort
    ) {
        this.employeeRepositoryPort = employeeRepositoryPort;
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    public Employee execute(Long id, Employee updatedEmployee, Long departmentId) {

        Employee existingEmployee = employeeRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        Department department = departmentRepositoryPort.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setHireDate(updatedEmployee.getHireDate());
        existingEmployee.setDepartment(department);

        return employeeRepositoryPort.save(existingEmployee);
    }
}