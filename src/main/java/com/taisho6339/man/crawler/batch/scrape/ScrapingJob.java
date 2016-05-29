package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.batch.common.CollectDataJob;
import com.taisho6339.man.crawler.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    EmployeeService service;

    @Override
    public void collectData() {
        List<Topic> results = scraper.scrape();
        if (results == null || results.isEmpty()) {
            return;
        }

        for (Topic result : results) {
            Employee employee = result.employee;
            Article article = result.article;
            Tag tag = result.tag;

            Employee registeredEmp = service.findByName(employee.getName());
            if (registeredEmp != null) {
                continue;
            }
            service.save(employee);
        }
    }
}
