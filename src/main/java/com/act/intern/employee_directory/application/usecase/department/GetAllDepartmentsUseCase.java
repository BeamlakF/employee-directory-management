package com.act.intern.employee_directory.application.usecase.department;

import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllDepartmentsUseCase {

    private final DepartmentRepositoryPort departmentRepositoryPort;

    public GetAllDepartmentsUseCase(DepartmentRepositoryPort departmentRepositoryPort) {
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    public List<Department> execute() {
        return departmentRepositoryPort.findAll();
    }
}