package com.act.intern.employee_directory.service;

import com.act.intern.employee_directory.domain.Department;
import com.act.intern.employee_directory.domain.Employee;
import com.act.intern.employee_directory.dto.EmployeeRequest;
import com.act.intern.employee_directory.repository.DepartmentRepository;
import com.act.intern.employee_directory.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees(Long departmentId) {

        if (departmentId != null) {
            return employeeRepository.findByDepartmentId(departmentId);
        }

        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Transactional
    public Employee createEmployee(EmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .salary(request.getSalary())
                .hireDate(request.getHireDate())
                .department(department)
                .build();

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = getEmployeeById(id);

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }
}