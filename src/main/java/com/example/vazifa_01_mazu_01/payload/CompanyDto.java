package com.example.vazifa_01_mazu_01.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "corpName bow bolmasligi kk")
    private String corpName;

    @NotNull(message = "directorName bow bolmasligi kk")
    private String directorName;

    @NotNull(message = "addressId bow bolmasligi kk")
    private Integer addressId;
}
