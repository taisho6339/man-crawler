package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.service.EmpTagRelationService;
import com.taisho6339.man.crawler.service.EmployeeService;
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
 * Created by sakamohiroki on 2016/05/31.
 */
@Controller
@RequestMapping(value = "/emp/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "tag/{tagId}")
    public String tagEmp(Model model, @PathVariable Long tagId) {
        model.addAttribute("employees", employeeService.findEmployeesBytagId(tagId));
        return "emplist";
    }

    @RequestMapping(value = "search", params = "keyword", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String keyword) {
        List<Employee> employees = employeeService.findLikeByName(keyword);
        model.addAttribute("employees", employees);
        return "emplist";
    }
}
