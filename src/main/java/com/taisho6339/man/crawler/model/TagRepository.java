package com.taisho6339.man.crawler.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByTagName(String tagName);
}
