package com.taisho6339.man.crawler;

import com.taisho6339.man.crawler.scrape.OfficialBlogScraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
//        OfficialBlogScraper scraper = new OfficialBlogScraper();
//        scraper.scrape();
    }
}
