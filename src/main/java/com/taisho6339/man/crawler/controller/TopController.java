package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.TagEmployeeRel;
import com.taisho6339.man.crawler.service.ArticleService;
import com.taisho6339.man.crawler.service.EmployeeService;
import com.taisho6339.man.crawler.service.TagRelService;
import com.taisho6339.man.crawler.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Controller
@RequestMapping(value = "/")
public class TopController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRelService tagRelService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        List<Employee> employees = jdbcTemplate.query("SELECT * " +
                        "FROM M_EMPLOYEE AS E " +
                        "INNER JOIN (SELECT * FROM T_TAG_EMP_REL WHERE T_TAG_EMP_REL.tag_id = ?) AS T " +
                        "ON E.id = T.emp_id", new Object[]{tagId},
                (ResultSet rs, int i) -> {
                    Employee employee = new Employee();
                    employee.setId(rs.getLong("emp_id"));
                    employee.setName(rs.getString("name"));
                    employee.setOrgName(rs.getString("org_name"));
                    return employee;
                });
        model.addAttribute("employees", employees);
//        List<TagEmployeeRel> rels = tagRelService.findByTagId(tagId);
//        for (TagEmployeeRel rel : rels) {
//            List<Employee> employees = rel.getEmployees();
//            System.out.println("size:" + employees.size());
//            for (Employee employee : employees) {
//                System.out.println(employee.toString());
//            }
//        }
        return "emplist";
    }
}
