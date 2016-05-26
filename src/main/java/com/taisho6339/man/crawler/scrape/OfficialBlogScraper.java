package com.taisho6339.man.crawler.scrape;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import groovy.lang.Tuple2;
import javafx.util.Pair;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Component
public class OfficialBlogScraper {

    private static final String BLOG_URL = "http://recruit-tech.co.jp/news/media-release/";

    public Tuple2<List<Employee>, List<Article>> scrape() {
        Document document = null;
        List<Employee> employees = new ArrayList<>();
        List<Article> articles = new ArrayList<>();

        //全体のHTML取得
        try {
            document = Jsoup.connect(BLOG_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Elements elements = document.getElementsByClass("media-release");
        for (Element element : elements) {
            Article article = findArticleFromElement(element);
            Employee employee = findEmployeeFromElement(element);
            if (employee != null) {
                employees.add(employee);
            }
            articles.add(article);
        }

        return new Tuple2<>(employees, articles);
    }

    private Article findArticleFromElement(Element element) {
        Article article = new Article();
        String aboutUrl = "";
        String summary = "";

        Elements linkElements = element.getElementsByClass("cf");
        if (linkElements != null && !linkElements.isEmpty()) {
            aboutUrl = element.getElementsByClass("cf").get(0).attr("href");
        }
        summary = element.text();

        article.setAboutUrl(aboutUrl);
        article.setSummary(summary);
        return article;
    }

    private Employee findEmployeeFromElement(Element element) {
        Employee employee = new Employee();
        String desc = element.text();
        //TODO 組織と名前が一緒になっている
        String orgName = extractNameOrg(desc);
        if (orgName != null) {
            employee.setName(orgName);
        }
        return employee;
    }

    private String extractNameOrg(String desc) {
        String regex = "(\\[(.+)\\])|(［(.+)］)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(desc);

        if (m.find()) {
            System.out.println(m.group());
            return m.group();
        }
        return null;
    }

//    private Pair<List<Employee>, List<Article>> scrape
}
