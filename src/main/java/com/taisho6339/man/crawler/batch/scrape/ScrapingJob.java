package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.batch.common.CollectDataJob;
import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.EmpTagRelation;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.model.Topic;
import com.taisho6339.man.crawler.service.ArticleService;
import com.taisho6339.man.crawler.service.EmpTagRelationService;
import com.taisho6339.man.crawler.service.EmployeeService;
import com.taisho6339.man.crawler.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * スクレイピングで得られたデータをDBに入れる
 * Created by sakamohiroki on 2016/05/26.
 */
@Component
public class ScrapingJob implements CollectDataJob {
    //TODO DIで入れるようにする
    @Autowired
    OfficialBlogScraper scraper;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @Autowired
    EmpTagRelationService empTagRelationService;

    @Override
    public void collectData() {
        List<Topic> results = scraper.scrape();
        if (results == null || results.isEmpty()) {
            return;
        }

        for (Topic result : results) {
            saveTopicInfo(result);
        }
    }

    @Transactional
    private void saveTopicInfo(Topic result) {
        //タグの登録
        Tag tag = result.tag;
        if (tag == null) {
            return;
        }

        Tag registeredTag = tagService.findByName(tag.getTagName());
        if (registeredTag == null) {
            registeredTag = tagService.save(tag);
        }

        //社員登録
        Employee employee = result.employee;
        Employee registeredEmp = employeeService.findByName(employee.getName());
        if (registeredEmp == null) {
            registeredEmp = employeeService.save(employee);
        }

        //記事登録
        Article article = result.article;
        article.setEmpId(registeredEmp.getId());
        articleService.save(article);

        //タグと社員の関連を登録
        EmpTagRelation empTagRelation = empTagRelationService.findByTagIdAndEmpId(registeredTag.getId(), registeredEmp.getId());
        if (empTagRelation != null) {
            return;
        }
        empTagRelation = new EmpTagRelation();
        empTagRelation.setEmpId(registeredEmp.getId());
        empTagRelation.setTagId(registeredTag.getId());
        empTagRelationService.save(empTagRelation);
    }
}
