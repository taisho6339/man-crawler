package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.TagEmployeeRel;
import com.taisho6339.man.crawler.service.ArticleService;
import com.taisho6339.man.crawler.service.EmployeeService;
import com.taisho6339.man.crawler.service.TagRelService;
import com.taisho6339.man.crawler.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Controller
@RequestMapping(value = "/")
public class TopController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @Autowired
    TagRelService tagRelService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "search", params = "keyword", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String keyword) {
        List<Employee> employees = employeeService.findLikeByName(keyword);
        model.addAttribute("employees", employees);
        return "emplist";
    }

    @RequestMapping(value = "articles/{emp_id}")
    public String articles(Model model, @PathVariable Long emp_id) {
        List<Article> articles = articleService.findByEmpId(emp_id);
        model.addAttribute("articles", articles);
        return "articles";
    }

    @RequestMapping(value = "tags")
    public String tags(Model model) {
        model.addAttribute("tags", tagService.findAll());
        return "tags";
    }

    @RequestMapping(value = "tag/{tagId}")
    public String tagEmp(Model model, @PathVariable Long tagId) {
        List<TagEmployeeRel> rels = tagRelService.findByTagId(tagId);
        for (TagEmployeeRel rel : rels) {
            System.out.println(rel.getTagId());
            List<Employee> employees = rel.getEmployees();
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
        }
        return "emplist";
    }
}
