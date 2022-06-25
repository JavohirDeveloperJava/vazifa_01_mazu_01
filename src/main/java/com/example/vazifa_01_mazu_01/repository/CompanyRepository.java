package com.example.vazifa_01_mazu_01.repository;

import com.example.vazifa_01_mazu_01.entity.Address;
import com.example.vazifa_01_mazu_01.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    boolean existsByAddressAndCorpNameAndDirectorName(Address address, String corpName, String directorName);
}
