package com.act.intern.employee_directory.infrastructure.persistence.repository;

import com.act.intern.employee_directory.infrastructure.persistence.entity.EmployeeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeJpaEntity, Long> {
    boolean existsByEmail(String email);
    List<EmployeeJpaEntity> findByDepartmentId(Long departmentId);
}