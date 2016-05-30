package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.batch.common.CollectDataJob;
import com.taisho6339.man.crawler.model.*;
import com.taisho6339.man.crawler.service.ArticleService;
import com.taisho6339.man.crawler.service.EmployeeService;
import com.taisho6339.man.crawler.service.TagRelService;
import com.taisho6339.man.crawler.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    TagRelService tagRelService;

    @Override
    public void collectData() {
        List<Topic> results = scraper.scrape();
        if (results == null || results.isEmpty()) {
            return;
        }

        for (Topic result : results) {
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

            //タグの登録
            Tag tag = result.tag;
            if (tag == null) {
                continue;
            }

            Tag registeredTag = tagService.findByName(tag.getTagName());
            if (registeredTag == null) {
                registeredTag = tagService.save(tag);
            }

            //タグと社員の関連を登録
            TagEmployeeRel registeredRel = tagRelService.findByTagIdAndEmpId(registeredTag.getId(), registeredEmp.getId());
            if (registeredRel != null) {
                continue;
            }
            TagEmployeeRel tagEmployeeRel = new TagEmployeeRel();
            tagEmployeeRel.setTagId(registeredTag.getId());
            tagEmployeeRel.setEmpId(registeredEmp.getId());
            tagRelService.save(tagEmployeeRel);
        }
    }
}
