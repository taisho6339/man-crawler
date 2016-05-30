package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.ArticleService;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.EmployeeService;

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

//
//
//    @Autowired
//    ArticleService articleService;
//
//    @RequestMapping("/list")
//    public List<Employee> employeeList() {
//        return employeeService.findAll();
//    }
//
//    @RequestMapping("/articles/{emp_id}")
//    public List<Article> articleList(@PathVariable Long emp_id) {
//        return articleService.findByEmpId(emp_id);
//    }
}
