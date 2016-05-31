package com.taisho6339.man.crawler.repository;

import com.taisho6339.man.crawler.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taisho6339 on 16/05/30.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByName(String name);

    @Query(name = "Employee.findLikeByName")
    public List<Employee> findLikeByName(@Param("name") String name);
}
