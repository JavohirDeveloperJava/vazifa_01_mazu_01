package com.example.vazifa_01_mazu_01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "name bow bolmasligi kk")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "companiy bow bolmasligi kk")
    @ManyToOne(optional = false)
    private Company company;
}
