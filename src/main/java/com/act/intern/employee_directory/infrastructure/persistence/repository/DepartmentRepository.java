package com.act.intern.employee_directory.infrastructure.persistence.repository;

import com.act.intern.employee_directory.domain.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String name);
}