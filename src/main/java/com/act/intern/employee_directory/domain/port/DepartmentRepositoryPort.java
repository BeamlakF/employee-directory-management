package com.act.intern.employee_directory.domain.port;

import com.act.intern.employee_directory.domain.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepositoryPort {
    Department save(Department department);
    Optional<Department> findById(Long id);
    List<Department> findAll();
    void deleteById(Long id);
    void delete(Department department);
    boolean existsByName(String name);  // Add this method
}