package com.act.intern.employee_directory.service;

import com.act.intern.employee_directory.domain.Department;
import com.act.intern.employee_directory.dto.DepartmentRequest;
import com.act.intern.employee_directory.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Transactional
    public Department createDepartment(DepartmentRequest request) {

        if (departmentRepository.existsByName(request.getName())) {
            throw new RuntimeException("Department name already exists");
        }

        Department department = Department.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, DepartmentRequest request) {

        Department department = getDepartmentById(id);

        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long id) {

        Department department = getDepartmentById(id);

        departmentRepository.delete(department);
    }
}