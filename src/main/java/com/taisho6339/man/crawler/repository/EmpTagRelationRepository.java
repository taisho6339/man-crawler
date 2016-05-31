package com.taisho6339.man.crawler.repository;

import com.taisho6339.man.crawler.model.EmpTagRelation;

import com.taisho6339.man.crawler.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/31.
 */
public interface EmpTagRelationRepository extends JpaRepository<EmpTagRelation, Long> {
    public List<EmpTagRelation> findByTagId(Long tagId);

    public List<EmpTagRelation> findByEmpId(Long empId);

    @Query(name = "EmpTagRelation.findByTagIdAndEmpId")
    public EmpTagRelation findByTagIdAndEmpId(@Param("tag_id") Long tagId, @Param("emp_id") Long empId);
}
