package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.TagEmployeeRel;
import com.taisho6339.man.crawler.model.TagRelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<TagEmployeeRel> findByTagId(Long tagId) {
        return tagRelRepository.findByTagId(tagId);
    }

    public TagEmployeeRel findByTagIdAndEmpId(Long tagId, Long empId) {
        return tagRelRepository.findByTagIdAndEmpId(tagId, empId);
    }
}
