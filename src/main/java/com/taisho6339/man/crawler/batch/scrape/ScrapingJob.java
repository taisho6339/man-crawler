package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.batch.common.CollectDataJob;
import com.taisho6339.man.crawler.model.Topic;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingJob.class);

    //TODO DIで入れるようにする
    @Autowired
    OfficialBlogScraper scraper;

    @Override
    public void collectData() {
        List<Topic> results = scraper.scrape();
        Topic topic = results.get(0);
        System.out.println(topic.employee.toString());
        System.out.println(topic.article.toString());
        System.out.println(topic.tag.toString());

        LOGGER.error("Call Scraping Job!!!!!!!!");
    }
}
