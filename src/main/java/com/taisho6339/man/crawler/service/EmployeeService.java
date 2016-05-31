package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.EmployeeRepository;
import com.taisho6339.man.crawler.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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

    public Employee findByName(String name) {
        return repository.findByName(name);
    }

    public List<Employee> findLikeByName(String name) {
        return repository.findLikeByName(name);
    }
}
