package com.act.intern.employee_directory.web.controller;

import com.act.intern.employee_directory.application.usecase.department.*;
import com.act.intern.employee_directory.domain.model.Department;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final GetAllDepartmentsUseCase getAllDepartmentsUseCase;
    private final GetDepartmentByIdUseCase getDepartmentByIdUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;

    public DepartmentController(
            CreateDepartmentUseCase createDepartmentUseCase,
            GetAllDepartmentsUseCase getAllDepartmentsUseCase,
            GetDepartmentByIdUseCase getDepartmentByIdUseCase,
            UpdateDepartmentUseCase updateDepartmentUseCase,
            DeleteDepartmentUseCase deleteDepartmentUseCase
    ) {
        this.createDepartmentUseCase = createDepartmentUseCase;
        this.getAllDepartmentsUseCase = getAllDepartmentsUseCase;
        this.getDepartmentByIdUseCase = getDepartmentByIdUseCase;
        this.updateDepartmentUseCase = updateDepartmentUseCase;
        this.deleteDepartmentUseCase = deleteDepartmentUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody Department department) {
        return createDepartmentUseCase.execute(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return getAllDepartmentsUseCase.execute();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return getDepartmentByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department
    ) {
        return updateDepartmentUseCase.execute(id, department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        deleteDepartmentUseCase.execute(id);
    }
}