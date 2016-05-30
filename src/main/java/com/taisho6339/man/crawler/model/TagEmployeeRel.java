package com.taisho6339.man.crawler.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Entity
@Table(name = "T_TAG_EMP_REL")
public class TagEmployeeRel {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long empId;

    @NotNull
    private Long tagId;

    @ManyToMany(targetEntity = Employee.class)
    @JoinColumn(name = "id")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
