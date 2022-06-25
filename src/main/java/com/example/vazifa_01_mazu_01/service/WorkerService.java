package com.example.vazifa_01_mazu_01.service;

import com.example.vazifa_01_mazu_01.entity.Address;
import com.example.vazifa_01_mazu_01.entity.Department;
import com.example.vazifa_01_mazu_01.entity.Worker;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.payload.WorkerDto;
import com.example.vazifa_01_mazu_01.repository.AddressRepository;
import com.example.vazifa_01_mazu_01.repository.DepartmentRepository;
import com.example.vazifa_01_mazu_01.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse add(WorkerDto dto){
        boolean exists = workerRepository.existsByPhoneNumber(dto.getPhoneNumber());
        if (exists){
            return new ApiResponse("Bunday ishchi mavjut",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Bunday address mavjut emas",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(dto.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday deparment mavjut emas",false);
        }


        Worker worker=new Worker();
        worker.setName(dto.getName());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setPhoneNumber(dto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("Worker saqlandi",true);
    }

    public List<Worker> get(){
        return workerRepository.findAll();
    }

    public Worker getId(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent()){
            Worker worker = optionalWorker.get();
            return worker;
        }
        return null;
    }

    public ApiResponse delet(Integer id){
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("worker ochirildi",true);
        }catch (Exception e){
            return new ApiResponse("hatolik ochmadi",false);
        }
    }

    public ApiResponse put(Integer id,WorkerDto dto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()){
            return new ApiResponse("Bunday worker mavjut emas",false);
        }
        boolean exists = workerRepository.existsByPhoneNumber(dto.getPhoneNumber());
        if (exists){
            return new ApiResponse("Bunday worker mavjut ",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Bunday address mavjut emas ",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(dto.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday department mavjut emas ",false);
        }

        Worker worker = optionalWorker.get();
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setName(dto.getName());
        workerRepository.save(worker);
        return new ApiResponse("worker ozgartirildi",true);
    }
}
