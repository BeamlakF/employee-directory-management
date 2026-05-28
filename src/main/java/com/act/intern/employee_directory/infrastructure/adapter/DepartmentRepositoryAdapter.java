package com.act.intern.employee_directory.infrastructure.adapter;

import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import com.act.intern.employee_directory.infrastructure.persistence.repository.DepartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepositoryAdapter implements DepartmentRepositoryPort {

    private final DepartmentRepository departmentRepository;

    public DepartmentRepositoryAdapter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void delete(Department department) {
        departmentRepository.delete(department);
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepository.existsByName(name);
    }
}