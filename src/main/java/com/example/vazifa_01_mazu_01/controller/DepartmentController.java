package com.example.vazifa_01_mazu_01.controller;

import com.example.vazifa_01_mazu_01.entity.Department;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.payload.DepartmentDto;
import com.example.vazifa_01_mazu_01.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/depar")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public HttpEntity<ApiResponse> add(@RequestBody DepartmentDto dto) {
        ApiResponse add = departmentService.add(dto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @GetMapping
    public HttpEntity<List<Department>> get() {
        List<Department> departments = departmentService.get();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id, @RequestBody DepartmentDto dto) {
        ApiResponse put = departmentService.put(id, dto);
        return ResponseEntity.status(put.isSuccess() ? 201 : 409).body(put);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delet(@PathVariable Integer id) {
        ApiResponse delet = departmentService.delet(id);
        return ResponseEntity.status(delet.isSuccess() ? 202 : 409).body(delet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getId(@PathVariable Integer id) {
        Department id1 = departmentService.getId(id);
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
