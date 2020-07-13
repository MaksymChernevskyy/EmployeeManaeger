package com.project.business.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "team")
@Data
public class Team {

    @Column(name = "name")
    private String name;

    @Column(name = "employee")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employee;
}
