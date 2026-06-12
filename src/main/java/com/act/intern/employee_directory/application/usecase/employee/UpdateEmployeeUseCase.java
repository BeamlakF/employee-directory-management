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

    // This matches your controller call: execute(id, employee, departmentId)
    public Employee execute(Long id, Employee updatedEmployee, Long departmentId) {

        // Fetch existing employee
        Employee existingEmployee = employeeRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        // Fetch department
        Department department = departmentRepositoryPort.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        // Create new Employee instance with updated values (immutable approach)
        Employee newEmployee = Employee.builder()
                .id(existingEmployee.getId())  // Keep the same ID
                .firstName(updatedEmployee.getFirstName() != null ?
                        updatedEmployee.getFirstName() : existingEmployee.getFirstName())
                .lastName(updatedEmployee.getLastName() != null ?
                        updatedEmployee.getLastName() : existingEmployee.getLastName())
                .email(updatedEmployee.getEmail() != null ?
                        updatedEmployee.getEmail() : existingEmployee.getEmail())
                .salary(updatedEmployee.getSalary() != null ?
                        updatedEmployee.getSalary() : existingEmployee.getSalary())
                .hireDate(updatedEmployee.getHireDate() != null ?
                        updatedEmployee.getHireDate() : existingEmployee.getHireDate())
                .department(department)
                .build();

        // Save and return
        return employeeRepositoryPort.save(newEmployee);
    }
}