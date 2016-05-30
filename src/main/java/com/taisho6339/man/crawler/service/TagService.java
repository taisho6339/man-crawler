package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.model.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }
}
