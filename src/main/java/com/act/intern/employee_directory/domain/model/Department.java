package com.act.intern.employee_directory.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Department {

    private Long id;

    private String name;

    private String description;
}