package com.redis.service;

import com.redis.dto.EmployeeDto;
import com.redis.entity.EmployeeEntity;
import com.redis.mapper.EmployeeDtoMapper;
import com.redis.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
    @Autowired
    private EmployeeRepository repository;

    @CachePut(cacheNames = "employeCache", key = "#entity.id")
    public EmployeeEntity save(EmployeeEntity entity) {
        return repository.save(entity);
    }

    @Cacheable(cacheNames = "employeCache", key = "#id")
    public EmployeeEntity searchEmployee(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable(cacheNames = "employeCache", key = "'all'")
    public Iterable<EmployeeEntity> findAll() {
        return repository.findAll();
    }
    @CacheEvict(cacheNames = "employeCache")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @CachePut(cacheNames = "employeCache", key = "#id")
    public EmployeeEntity update(Long id, EmployeeEntity updateDetails) {
        EmployeeEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity != null) {
            if (updateDetails.getEmployeeName() != null) {
                existingEntity.setEmployeeName(updateDetails.getEmployeeName());
            }
            if (updateDetails.getEmpEmail() != null) {
                existingEntity.setEmpEmail(updateDetails.getEmpEmail());
            }
            return repository.save(existingEntity);
        }
        return null;
    }

}
