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

        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setDescription(updatedDepartment.getDescription());

        return departmentRepositoryPort.save(existingDepartment);
    }
}