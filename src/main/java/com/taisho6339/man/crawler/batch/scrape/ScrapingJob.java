package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.batch.common.CollectDataJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * スクレイピングで得られたデータをDBに入れる
 * Created by sakamohiroki on 2016/05/26.
 */
@Component
public class ScrapingJob implements CollectDataJob {
    //TODO DIで入れるようにする
    @Autowired
    OfficialBlogScraper scraper;

    @Override
    public void collectData() {

    }
}
