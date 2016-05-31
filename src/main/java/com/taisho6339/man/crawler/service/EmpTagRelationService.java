package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.EmpTagRelation;
import com.taisho6339.man.crawler.repository.EmpTagRelationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/31.
 */
@Service
public class EmpTagRelationService {
    @Autowired
    private EmpTagRelationRepository empTagRelationRepository;


    public EmpTagRelation save(EmpTagRelation relation) {
        return empTagRelationRepository.save(relation);
    }

    public List<EmpTagRelation> findByTagId(Long tagId) {
        return empTagRelationRepository.findByTagId(tagId);
    }

    public EmpTagRelation findByTagIdAndEmpId(Long tagId, Long empId) {
        return empTagRelationRepository.findByTagIdAndEmpId(tagId, empId);
    }
}
