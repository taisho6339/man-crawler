package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.batch.common.CollectDataJob;
import com.taisho6339.man.crawler.model.*;

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

    @Override
    public void collectData() {
        List<Topic> results = scraper.scrape();
        if (results == null || results.isEmpty()) {
            return;
        }

        for (Topic result : results) {
            Employee employee = result.employee;
            Employee registeredEmp = employeeService.findByName(employee.getName());
            if (registeredEmp == null) {
                registeredEmp = employeeService.save(employee);
            }

            Article article = result.article;
            article.setEmpId(registeredEmp.getId());
            articleService.save(article);
            Tag tag = result.tag;
        }
    }
}
