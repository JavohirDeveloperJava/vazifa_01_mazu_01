package com.example.vazifa_01_mazu_01.service;

import com.example.vazifa_01_mazu_01.entity.Address;
import com.example.vazifa_01_mazu_01.entity.Company;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.payload.CompanyDto;
import com.example.vazifa_01_mazu_01.repository.AddressRepository;
import com.example.vazifa_01_mazu_01.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;


    /**
     * add company
     * @param dto
     * @return
     */
    public ApiResponse add(CompanyDto dto){
        Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressId());
        if (optionalAddress.isEmpty()){
            return new ApiResponse("Bunday addres mavjut emas",false);
        }
//        boolean exists = companyRepository.existsByAddressAndCorpNameAndDirectorName(optionalAddress.get(), dto.getCorpName(), dto.getDirectorName());
//        if (exists){
//            return new ApiResponse("Bunday company mavjut",false);
//        }
        Company company=new Company();
        company.setCorpName(dto.getCorpName());
        company.setDirectorName(dto.getDirectorName());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("Company saqlandi", true);
    }

    /**
     * get company
     * @return
     */
    public List<Company> get(){
        List<Company> all = companyRepository.findAll();
        return all;
    }


    /**
     * ID orqalik companiyni olish
     * @param id
     * @return
     */
    public Company getId(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            return company;
        }
        return null;
    }

    public ApiResponse put(Integer id,CompanyDto dto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bunday companiy mavjut emas",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressId());
        if (!optionalAddress.isEmpty()){
            return new ApiResponse("Bunday address mavjut emas",false);
        }
        Company company = optionalCompany.get();
        company.setAddress(optionalAddress.get());
        company.setCorpName(dto.getCorpName());
        company.setDirectorName(dto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("Company ozgardi",true);
    }

    public ApiResponse delet(Integer id){
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Company ochdi",true);
        }catch (Exception e){
            return new ApiResponse("hatolik companiy ochadi",false);
        }
    }




}
