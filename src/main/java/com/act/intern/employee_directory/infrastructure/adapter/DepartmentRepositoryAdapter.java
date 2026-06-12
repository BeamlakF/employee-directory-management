package com.act.intern.employee_directory.infrastructure.adapter;

import com.act.intern.employee_directory.domain.model.Department;
import com.act.intern.employee_directory.domain.port.DepartmentRepositoryPort;
import com.act.intern.employee_directory.infrastructure.persistence.entity.DepartmentJpaEntity;
import com.act.intern.employee_directory.infrastructure.persistence.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DepartmentRepositoryAdapter implements DepartmentRepositoryPort {

    private final DepartmentRepository departmentRepository;

    public DepartmentRepositoryAdapter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        DepartmentJpaEntity entity = DepartmentJpaEntity.fromDomain(department);
        DepartmentJpaEntity savedEntity = departmentRepository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentJpaEntity::toDomain);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll().stream()
                .map(DepartmentJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void delete(Department department) {
        if (department != null && department.getId() != null) {
            departmentRepository.deleteById(department.getId());
        }
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepository.existsByName(name);
    }
}