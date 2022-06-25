package com.example.vazifa_01_mazu_01.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private Integer addressId;
    private Integer departmentId;
}
