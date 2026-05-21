package com.act.intern.employee_directory.repository;

import com.act.intern.employee_directory.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);

    List<Employee> findByDepartmentId(Long departmentId);
}