package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.scrape.OfficialBlogScraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import groovy.lang.Tuple2;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Service
public class EmployeeCollectService {
    //TODO DIで入れる
    @Autowired
    OfficialBlogScraper scraper;

    public Tuple2<List<Employee>, List<Article>> findInfo() {
        return scraper.scrape();
    }
}
