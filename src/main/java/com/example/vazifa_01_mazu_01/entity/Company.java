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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "corpName bow bolmasligi kk")
    @Column(nullable = false)
    private String corpName;

    @NotNull(message = "directorName bow bolmasligi kk")
    @Column(nullable = false)
    private String directorName;

    @NotNull(message = "address bow bolmasligi kk")
    @OneToOne(optional = false)
    private Address address;
}
