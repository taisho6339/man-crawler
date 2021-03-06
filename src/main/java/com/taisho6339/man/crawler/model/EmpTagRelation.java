package com.taisho6339.man.crawler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by sakamohiroki on 2016/05/31.
 */
@Entity
@Table(name = "T_EMP_TAG")
public class EmpTagRelation {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long empId;

    @NotNull
    private Long tagId;

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
