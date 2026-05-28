package com.act.intern.employee_directory.web.controller;

import com.act.intern.employee_directory.application.usecase.employee.*;
import com.act.intern.employee_directory.domain.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final GetEmployeesByDepartmentUseCase getEmployeesByDepartmentUseCase;

    public EmployeeController(
            CreateEmployeeUseCase createEmployeeUseCase,
            GetAllEmployeesUseCase getAllEmployeesUseCase,
            GetEmployeeByIdUseCase getEmployeeByIdUseCase,
            UpdateEmployeeUseCase updateEmployeeUseCase,
            DeleteEmployeeUseCase deleteEmployeeUseCase,
            GetEmployeesByDepartmentUseCase getEmployeesByDepartmentUseCase
    ) {
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.getAllEmployeesUseCase = getAllEmployeesUseCase;
        this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.deleteEmployeeUseCase = deleteEmployeeUseCase;
        this.getEmployeesByDepartmentUseCase = getEmployeesByDepartmentUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(
            @RequestBody Employee employee,
            @RequestParam Long departmentId
    ) {
        return createEmployeeUseCase.execute(employee, departmentId);
    }

    @GetMapping
    public List<Employee> getAllEmployees(
            @RequestParam(required = false) Long departmentId
    ) {

        if (departmentId != null) {
            return getEmployeesByDepartmentUseCase.execute(departmentId);
        }

        return getAllEmployeesUseCase.execute();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return getEmployeeByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee,
            @RequestParam Long departmentId
    ) {
        return updateEmployeeUseCase.execute(id, employee, departmentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        deleteEmployeeUseCase.execute(id);
    }
}