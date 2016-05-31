package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by taisho6339 on 16/05/30.
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public Employee findById(Long id) {
        return repository.findById(id);
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

    public List<Employee> findEmployeesBytagId(Long tagId) {
        return jdbcTemplate.query("SELECT * " +
                        "FROM M_EMPLOYEE AS E " +
                        "INNER JOIN (SELECT * FROM T_EMP_TAG WHERE T_EMP_TAG.tag_id = ?) AS T " +
                        "ON E.id = T.emp_id", new Object[]{tagId},
                (ResultSet rs, int i) -> {
                    Employee employee = new Employee();
                    employee.setId(rs.getLong("emp_id"));
                    employee.setName(rs.getString("name"));
                    employee.setOrgName(rs.getString("org_name"));
                    return employee;
                });
    }
}
