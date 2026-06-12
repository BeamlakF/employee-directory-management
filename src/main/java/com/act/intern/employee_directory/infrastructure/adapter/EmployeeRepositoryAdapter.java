package com.act.intern.employee_directory.infrastructure.adapter;

import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import com.act.intern.employee_directory.infrastructure.persistence.entity.EmployeeJpaEntity;
import com.act.intern.employee_directory.infrastructure.persistence.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

    private final EmployeeRepository employeeRepository;

    public EmployeeRepositoryAdapter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeJpaEntity entity = EmployeeJpaEntity.fromDomain(employee);
        EmployeeJpaEntity savedEntity = employeeRepository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id)
                .map(EmployeeJpaEntity::toDomain);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void delete(Employee employee) {
        if (employee != null && employee.getId() != null) {
            employeeRepository.deleteById(employee.getId());
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public List<Employee> findByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId).stream()
                .map(EmployeeJpaEntity::toDomain)
                .collect(Collectors.toList());
    }
}