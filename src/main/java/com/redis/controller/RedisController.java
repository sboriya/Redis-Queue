package com.redis.controller;

import com.redis.entity.EmployeeEntity;
import com.redis.service.EmpService;
import com.redis.service.RedisQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/cache")
public class RedisController{
    @Autowired
    private EmpService service;

    @Autowired
    RedisQueueService redisQueueService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody EmployeeEntity employeeEntity) {
        redisQueueService.addToQueue(employeeEntity);
        return ResponseEntity.ok("Request is in queue");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> findById(@PathVariable Long id) {
        Optional<EmployeeEntity> employeeDto = Optional.ofNullable(service.searchEmployee(id));
        return employeeDto
                .map(entity -> ResponseEntity.ok(entity))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/allEmployee")
    public ResponseEntity<Iterable<EmployeeEntity>> findAll() {
        Iterable<EmployeeEntity> employees = service.findAll();
        return ResponseEntity.ok(employees);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable Long id, @RequestBody EmployeeEntity entity) {
        EmployeeEntity updatedDto = service.update(id, entity);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }
}
