package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Employee;
import com.taisho6339.man.crawler.model.Tag;
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
import java.util.Set;

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
        //TODO JDBCでjoinするバージョン。個人的にはinsert実行コストが減るのでこちらのほうが好き
//        List<Employee> employees = jdbcTemplate.query("SELECT * " +
//                        "FROM M_EMPLOYEE AS E " +
//                        "INNER JOIN (SELECT * FROM T_TAG_EMP_REL WHERE T_TAG_EMP_REL.tag_id = ?) AS T " +
//                        "ON E.id = T.emp_id", new Object[]{tagId},
//                (ResultSet rs, int i) -> {
//                    Employee employee = new Employee();
//                    employee.setId(rs.getLong("emp_id"));
//                    employee.setName(rs.getString("name"));
//                    employee.setOrgName(rs.getString("org_name"));
//                    return employee;
//                });
        Tag tag = tagService.findById(tagId);
        Set<Employee> employeeSet = tag.getEmployees();
        model.addAttribute("employees", employeeSet);
        return "emplist";
    }

    @RequestMapping(value = "search", params = "keyword", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String keyword) {
        List<Employee> employees = employeeService.findLikeByName(keyword);
        model.addAttribute("employees", employees);
        return "emplist";
    }
}
