package com.taisho6339.man.crawler.scrape;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
public class OfficialBlogScraper {

    private static final String BLOG_URL = "http://recruit-tech.co.jp/news/media-release/";

    public void scrape() {
        List<Employee> employees = new ArrayList<>();
        List<Article> articles = new ArrayList<>();

        try {
            Document document = Jsoup.connect(BLOG_URL).get();
            Elements elements = document.getElementsByClass("media-release");
            System.out.println("element size:" + elements.size());
            System.out.println("employee size:" + employees.size());

            for (Element element : elements) {
                Article article = findArticleFromElement(element);
                Employee employee = findEmployeeFromElement(element);
                if (article == null || employee == null) {
                    continue;
                }
                employees.add(employee);
                articles.add(article);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        String[] orgName = extractNameOrg(desc);
        if (orgName == null || orgName.length != 2) {
            return null;
        }
        employee.setOrganization(orgName[0]);
        employee.setName(orgName[1]);

        return employee;
    }

    private String[] extractNameOrg(String desc) {
//        desc = "弊社エンジニアの執筆した技術書が刊行されました。 ～ドキュメント作成システム構築ガイド[GitHub、RedPen、Asciidoctor、CIによる モダンライティング] ［アドバンスドテクノロジーラボ 伊藤敬彦］";
        String regex = "(\\[(.+?)\\])|(［(.+)］)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(desc);
        if (!m.find()) {
            return null;
        }
        return m.group().split("\\s+");
    }

//    private Pair<List<Employee>, List<Article>> scrape
}
