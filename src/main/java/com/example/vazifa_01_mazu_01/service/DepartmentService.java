package com.example.vazifa_01_mazu_01.service;

import com.example.vazifa_01_mazu_01.entity.Company;
import com.example.vazifa_01_mazu_01.entity.Department;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.payload.DepartmentDto;
import com.example.vazifa_01_mazu_01.repository.CompanyRepository;
import com.example.vazifa_01_mazu_01.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse add(DepartmentDto dto){
        Optional<Company> optionalCompany = companyRepository.findById(dto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bunday companiy mavjut emas",false);
        }
        Department department=new Department();
        department.setName(dto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Deparment saqlandi",true);
    }

    public List<Department> get(){
        return departmentRepository.findAll();
    }

    public ApiResponse put(Integer id, DepartmentDto dto){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday department mavjut emas",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(dto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bunday company mavjut emas",false);
        }

        Department department = optionalDepartment.get();
        department.setName(dto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("deparment ozgardi",true);
    }

    public ApiResponse delet(Integer id){
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("Deparment ochdi",true);
        }catch (Exception e){
            return new ApiResponse("Hatolik ochmadi",false);
        }
    }

    public Department getId(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            Department department = optionalDepartment.get();
            return department;
        }
        return null;
    }
}
