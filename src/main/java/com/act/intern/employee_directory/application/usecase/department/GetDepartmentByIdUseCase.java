package com.act.intern.employee_directory.application.usecase.department;

import com.act.intern.employee_directory.domain.exception.ResourceNotFoundException;
import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentByIdUseCase {

    private final DepartmentRepositoryPort departmentRepositoryPort;

    public GetDepartmentByIdUseCase(DepartmentRepositoryPort departmentRepositoryPort) {
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    public Department execute(Long id) {

        return departmentRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));
    }
}