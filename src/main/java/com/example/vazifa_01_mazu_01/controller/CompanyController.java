package com.example.vazifa_01_mazu_01.controller;

import com.example.vazifa_01_mazu_01.entity.Company;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.payload.CompanyDto;
import com.example.vazifa_01_mazu_01.service.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /**
     * Company add
     * @param dto
     * @return
     */
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid  @RequestBody CompanyDto dto){
        ApiResponse add = companyService.add(dto);
        if (add.isSuccess()){
            return ResponseEntity.status(201).body(add);
        }
        return ResponseEntity.status(409).body(add);
    }

    /**
     * companyni hammasini olish
     * @return
     */
    @GetMapping
    public HttpEntity<List<Company>> get(){
        List<Company> companies = companyService.get();
        return ResponseEntity.ok(companies);
    }


    /**
     * ID orqalik companyni olish
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getId(@PathVariable Integer id){
        Company id1 = companyService.getId(id);
        return ResponseEntity.ok(id1);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> put(@PathVariable Integer id,@Valid @PathVariable CompanyDto dto){
        ApiResponse put = companyService.put(id, dto);
        if (put.isSuccess()) {
            return ResponseEntity.status(201).body(put);
        }
        return ResponseEntity.status(409).body(put);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet = companyService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?202:409).body(delet);
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
