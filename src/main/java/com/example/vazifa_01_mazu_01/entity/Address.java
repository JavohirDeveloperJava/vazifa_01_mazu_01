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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "street bow bolmasligi kk")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "home number bow bolmasligi kk")
    @Column(nullable = false)
    private String homeNumber;


}
