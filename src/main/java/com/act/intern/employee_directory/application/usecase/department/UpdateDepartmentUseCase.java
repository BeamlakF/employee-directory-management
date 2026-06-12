package com.act.intern.employee_directory.application.usecase.department;

import com.act.intern.employee_directory.domain.exception.ResourceNotFoundException;
import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateDepartmentUseCase {

    private final DepartmentRepositoryPort departmentRepositoryPort;

    public UpdateDepartmentUseCase(DepartmentRepositoryPort departmentRepositoryPort) {
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    public Department execute(Long id, Department updatedDepartment) {

        Department existingDepartment = departmentRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        // Create a new Department instance with updated values (immutable approach)
        Department newDepartment = Department.builder()
                .id(existingDepartment.getId())  // Keep the same ID
                .name(updatedDepartment.getName() != null ?
                        updatedDepartment.getName() : existingDepartment.getName())
                .description(updatedDepartment.getDescription() != null ?
                        updatedDepartment.getDescription() : existingDepartment.getDescription())
                .build();

        return departmentRepositoryPort.save(newDepartment);
    }
}