package com.example.vazifa_01_mazu_01.controller;

import com.example.vazifa_01_mazu_01.entity.Address;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addres")
public class AddressController {
    @Autowired
    AddressService addressService;
    @GetMapping
    public HttpEntity<List<Address>> get(){
        List<Address> addresses = addressService.get();
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public HttpEntity<ApiResponse> aad(@Valid @RequestBody Address address){
        ApiResponse add = addressService.add(address);
        if (add.isSuccess()){
            return ResponseEntity.status(201).body(add);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(add);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet = addressService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?202:409).body(delet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id,@Valid @RequestBody Address address){
        ApiResponse put = addressService.put(id, address);
        return ResponseEntity.status(put.isSuccess()? 202:409).body(put);
    }

    @GetMapping("{id}")
    public HttpEntity<Address> getId(@PathVariable Integer id){
        Address id1 = addressService.getId(id);
        return ResponseEntity.ok(id1);
    }













    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
