package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.controller.request.SearchRequest;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.service.EmployeeService;
import com.taisho6339.man.crawler.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.validation.Valid;

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

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model, @ModelAttribute("keyword") @Valid SearchRequest searchRequest, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("too large!!!!");
            for (FieldError fieldError : result.getFieldErrors()) {
                System.out.println(fieldError.getField());
                System.out.println(fieldError.getCode());
            }
            return "index";
        }

        List<Employee> employees = employeeService.findLikeByName(searchRequest.getQuery());
        model.addAttribute("employees", employees);
        return "emplist";
    }
}
