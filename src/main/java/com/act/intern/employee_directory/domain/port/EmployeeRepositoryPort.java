package com.act.intern.employee_directory.domain.port;

import com.act.intern.employee_directory.domain.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryPort {
    Employee save(Employee employee);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();
    void deleteById(Long id);
    void delete(Employee employee);  // You're missing this
    boolean existsByEmail(String email);
    List<Employee> findByDepartmentId(Long departmentId);
}