package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.TagEmployeeRel;
import com.taisho6339.man.crawler.model.TagRelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
@Service
public class TagRelService {
    @Autowired
    private TagRelRepository tagRelRepository;

    public TagEmployeeRel save(TagEmployeeRel rel) {
        return tagRelRepository.save(rel);
    }
}
