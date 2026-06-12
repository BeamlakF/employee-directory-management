package com.act.intern.employee_directory.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.act.intern.employee_directory.domain.model.Department;


@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Convert to domain model
    public Department toDomain() {
        return Department.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

    // Convert from domain model
    public static DepartmentJpaEntity fromDomain(Department department) {
        return new DepartmentJpaEntity(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
}