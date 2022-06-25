package com.example.vazifa_01_mazu_01.service;

import com.example.vazifa_01_mazu_01.entity.Address;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    /**
     * addressni barchasini olish
     * @return
     */
    public List<Address> get(){
        return addressRepository.findAll();
    }

    public ApiResponse add(Address address){
        Address address1=new Address();
        address1.setStreet(address.getStreet());
        address1.setHomeNumber(address.getHomeNumber());
        addressRepository.save(address1);
        return new ApiResponse("address saqlandi",true);
    }

    public ApiResponse delet(Integer id){
        try {
            addressRepository.deleteById(id);
            return new ApiResponse("address ochdi", true);
        }catch (Exception e){
            return new ApiResponse("address mavjut emas", false);
        }

    }

    public ApiResponse put(Integer id,Address address){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new ApiResponse("Bunday address mavjut emas",false);
        }

        Address address1 = optionalAddress.get();
        address1.setHomeNumber(address.getHomeNumber());
        address1.setStreet(address.getStreet());
        addressRepository.save(address1);
        return new ApiResponse("Address ozgartirildi",true);
    }

    public Address getId(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return  optionalAddress.orElse(null);
    }
}
