package com.act.intern.employee_directory.application.usecase.department;

import com.act.intern.employee_directory.domain.exception.DuplicateResourceException;
import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateDepartmentUseCase {

    private final DepartmentRepositoryPort departmentRepositoryPort;

    public CreateDepartmentUseCase(DepartmentRepositoryPort departmentRepositoryPort) {
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    public Department execute(Department department) {

        if (departmentRepositoryPort.existsByName(department.getName())) {
            throw new DuplicateResourceException("Department already exists");
        }

        return departmentRepositoryPort.save(department);
    }
}