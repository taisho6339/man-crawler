package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.ArticleService;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@RestController
@RequestMapping(value = "/api")
public class TopController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ArticleService articleService;

    @RequestMapping("/list")
    public List<Employee> employeeList() {
        return employeeService.findAll();
    }

    @RequestMapping("/articles/{emp_id}")
    public List<Article> articleList(@PathVariable Long emp_id) {
        return articleService.findByEmpId(emp_id);
    }
}
