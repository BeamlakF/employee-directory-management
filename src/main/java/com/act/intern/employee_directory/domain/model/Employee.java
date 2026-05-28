package com.act.intern.employee_directory.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

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
    private Department department;

    public void setFirstName(String firstName) {

        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be blank");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be blank");
        }

        this.lastName = lastName;
    }

    public void setEmail(String email) {

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        this.email = email;
    }

    public void setSalary(BigDecimal salary) {

        if (salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        this.salary = salary;
    }
}