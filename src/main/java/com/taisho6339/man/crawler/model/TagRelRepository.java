package com.taisho6339.man.crawler.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
public interface TagRelRepository extends JpaRepository<TagEmployeeRel, Long> {
    public List<TagEmployeeRel> findByTagId(Long tagId);
}
