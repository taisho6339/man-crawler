package com.taisho6339.man.crawler.batch.scrape;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.model.Topic;

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

/**
 * Tag情報、社員情報、記事情報をブログから収集する
 * Created by sakamohiroki on 2016/05/26.
 */
@Component
public class OfficialBlogScraper implements Scraper {

    private static final String BLOG_URL = "http://recruit-tech.co.jp/news/media-release/";

    private Document createBlogHtmlDoc() {
        //全体のHTML取得
        try {
            return Jsoup.connect(BLOG_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Topic> scrape() {
        List<Topic> results = new ArrayList<>();

        Document document = createBlogHtmlDoc();
        if (document == null) {
            return null;
        }

        Elements elements = document.getElementsByClass("media-release");
        for (Element element : elements) {
            Topic topic = new Topic();
            //社員が取れていない場合は情報を捨てる
            topic.employee = findEmployeeNameFromElement(element);
            if (topic.employee == null) {
                continue;
            }
            topic.article = findArticleFromElement(element);
            topic.tag = findTagFromElement(element);
            results.add(topic);
        }

        return results;
    }

    //タグ情報の取得
    private Tag findTagFromElement(Element element) {
        Elements tagElements = element.getElementsByClass("mt2016_tag");
        if (tagElements == null || tagElements.isEmpty()) {
            return null;
        }
        String tagName = tagElements.get(0).text();
        Tag tag = new Tag();
        tag.setTagName(tagName);
        return tag;
    }

    //記事情報の取得
    private Article findArticleFromElement(Element element) {
        Article article = new Article();
        String aboutUrl = "";
        String summary = "";

        Elements linkElements = element.getElementsByClass("cf");
        if (linkElements != null && !linkElements.isEmpty()) {
            aboutUrl = linkElements.get(0).attr("href");
        }

        Elements descElements = element.getElementsByClass("desc");
        if (descElements != null && !descElements.isEmpty()) {
            summary = descElements.get(0).text();
        }

        article.setAboutUrl(aboutUrl);
        article.setSummary(summary);
        return article;
    }

    //従業員情報の取得
    private Employee findEmployeeNameFromElement(Element element) {
        String desc = element.text();
        String orgName = extractNameOrg(desc);
        if (orgName == null) {
            return null;
        }
        Employee employee = new Employee();
        String[] employeeInfos = orgName.split("\\s");
        employee.setOrgName(employeeInfos[0]);
        //[組織名 名前]の構成になっていない場合
        if (employeeInfos.length > 2) {
            employee.setName(employeeInfos[employeeInfos.length - 1]);
        } else {
            employee.setName(employeeInfos[1]);
        }

        return employee;
    }

    private String extractNameOrg(String desc) {
        String regex = "(\\[(.+)\\s(.+)\\])|(［(.+)\\s(.+)］)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(desc);

        if (m.find()) {
            String orgName = m.group();
            orgName = orgName.replaceAll("[(\\[)(\\])［］]", "");
            return orgName;
        }
        return null;
    }
}
