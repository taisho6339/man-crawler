package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findByName(String name) {
        return tagRepository.findByTagName(name);
    }

    public Tag findById(Long id) {
        return tagRepository.findById(id);
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }
}
