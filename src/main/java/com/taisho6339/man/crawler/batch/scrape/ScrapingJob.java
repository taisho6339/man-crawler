package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.batch.common.CollectDataJob;
import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.model.Topic;
import com.taisho6339.man.crawler.service.ArticleService;
import com.taisho6339.man.crawler.service.EmployeeService;
import com.taisho6339.man.crawler.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            throw new NotFoundScrapeDataException();
        }

        Tag registeredTag = tagService.findByName(tag.getTagName());
        if (registeredTag == null) {
            registeredTag = tagService.save(tag);
        }

        //タグと社員の関連を登録
        Set<Tag> tags = registeredEmp.getTags();
        Set<Employee> employees = registeredTag.getEmployees();
        if (tags == null) {
            tags = new HashSet<>();
        }

        if (employees == null) {
            employees = new HashSet<>();
        }

        tags.add(registeredTag);
        employees.add(registeredEmp);
        registeredEmp.setTags(tags);
        registeredTag.setEmployees(employees);

        employeeService.save(registeredEmp);
        tagService.save(registeredTag);
    }

    private static class NotFoundScrapeDataException extends RuntimeException {
    }
}
