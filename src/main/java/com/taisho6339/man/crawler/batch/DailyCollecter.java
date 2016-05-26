package com.taisho6339.man.crawler.batch;

import com.taisho6339.man.crawler.batch.scrape.ScrapingJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 日時実行Job群。主にデータを収集する
 * Created by sakamohiroki on 2016/05/26.
 */
public class DailyCollecter {

    @Autowired
    ScrapingJob scrapingJob; //TODO DIで入れるようにする

    @Scheduled(cron = "* * * * *") //TODO どこかで一括管理するようにする
    public void runJob() {
        scrapingJob.collectData();
    }
}
