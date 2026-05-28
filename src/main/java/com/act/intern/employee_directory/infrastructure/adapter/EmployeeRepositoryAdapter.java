package com.act.intern.employee_directory.infrastructure.adapter;

import com.act.intern.employee_directory.domain.model.Employee;
import com.act.intern.employee_directory.domain.port.EmployeeRepositoryPort;
import com.act.intern.employee_directory.infrastructure.persistence.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

    private final EmployeeRepository employeeRepository;

    public EmployeeRepositoryAdapter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public List<Employee> findByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}