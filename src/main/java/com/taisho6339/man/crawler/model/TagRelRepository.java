package com.taisho6339.man.crawler.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
public interface TagRelRepository extends JpaRepository<TagEmployeeRel, Long> {
    public List<TagEmployeeRel> findByTagId(Long tagId);

    @Query(name = "TagEmployeeRel.findByTagIdAndEmpId")
    public TagEmployeeRel findByTagIdAndEmpId(@Param("tag_id") Long tagId, @Param("emp_id") Long empId);
}
