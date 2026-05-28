package com.act.intern.employee_directory.domain.port;

import com.act.intern.employee_directory.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryPort {

    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    void delete(Employee employee);

    boolean existsByEmail(String email);

    List<Employee> findByDepartmentId(Long departmentId);
}