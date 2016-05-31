package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.service.ArticleService;

import com.taisho6339.man.crawler.service.EmployeeService;
import com.taisho6339.man.crawler.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/31.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/{emp_id}")
    public String articles(Model model, @PathVariable Long emp_id) {
        Employee employee = employeeService.findById(emp_id);
        List<Article> articles = articleService.findByEmpId(emp_id);
        List<Tag> tags = tagService.findByEmpId(emp_id);
        model.addAttribute("employee", employee);
        model.addAttribute("tags", tags);
        model.addAttribute("articles", articles);
        return "articles";
    }
}
