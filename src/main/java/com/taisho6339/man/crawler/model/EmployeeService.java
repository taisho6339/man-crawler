package com.taisho6339.man.crawler.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by taisho6339 on 16/05/30.
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }
}
