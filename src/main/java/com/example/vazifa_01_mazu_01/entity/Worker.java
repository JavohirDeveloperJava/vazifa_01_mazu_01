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
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "name bow bolmasligi kk")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "phoneNumber bow bolmasligi kk")
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @NotNull(message = "address bow bolmasligi kk")
    @OneToOne(optional = false)
    private Address address;

    @NotNull(message = "department bow bolmasligi kk")
    @OneToOne(optional = false)
    private Department department;
}
