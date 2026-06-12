package com.act.intern.employee_directory.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.act.intern.employee_directory.domain.model.Employee;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentJpaEntity department;

    // Convert to domain model
    public Employee toDomain() {
        return Employee.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .salary(salary)
                .hireDate(hireDate)
                .department(department != null ? department.toDomain() : null)
                .build();
    }

    // Convert from domain model
    public static EmployeeJpaEntity fromDomain(Employee employee) {
        EmployeeJpaEntity entity = new EmployeeJpaEntity();
        entity.setId(employee.getId());
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setEmail(employee.getEmail());
        entity.setSalary(employee.getSalary());
        entity.setHireDate(employee.getHireDate());

        if (employee.getDepartment() != null) {
            entity.setDepartment(DepartmentJpaEntity.fromDomain(employee.getDepartment()));
        }

        return entity;
    }
}