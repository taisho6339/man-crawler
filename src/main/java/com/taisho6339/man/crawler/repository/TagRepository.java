package com.taisho6339.man.crawler.repository;

import com.taisho6339.man.crawler.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByTagName(String tagName);

    public Tag findById(Long id);
}
