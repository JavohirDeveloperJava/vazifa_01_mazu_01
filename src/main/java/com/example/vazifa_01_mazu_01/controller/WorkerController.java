package com.example.vazifa_01_mazu_01.controller;

import com.example.vazifa_01_mazu_01.entity.Worker;
import com.example.vazifa_01_mazu_01.payload.ApiResponse;
import com.example.vazifa_01_mazu_01.payload.WorkerDto;
import com.example.vazifa_01_mazu_01.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/wor")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody WorkerDto dto){
        ApiResponse add = workerService.add(dto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }


    @GetMapping ResponseEntity<List<Worker>> get(){
        List<Worker> workers = workerService.get();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getId(@PathVariable Integer id){
        Worker id1 = workerService.getId(id);
        return ResponseEntity.ok(id1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delet(@PathVariable Integer id){
        ApiResponse delet = workerService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?202:409).body(delet);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id,@Valid @RequestBody WorkerDto dto){
        ApiResponse put = workerService.put(id, dto);
        return ResponseEntity.status(put.isSuccess()?201:409).body(put);
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
