package com.act.intern.employee_directory.infrastructure.persistence.repository;

import com.act.intern.employee_directory.infrastructure.persistence.entity.DepartmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentJpaEntity, Long> {
    boolean existsByName(String name);
}