package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/31.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/{emp_id}")
    public String articles(Model model, @PathVariable Long emp_id) {
        List<Article> articles = articleService.findByEmpId(emp_id);
        model.addAttribute("articles", articles);
        return "articles";
    }
}
