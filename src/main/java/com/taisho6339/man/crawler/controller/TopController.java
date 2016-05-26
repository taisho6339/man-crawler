package com.taisho6339.man.crawler.controller;

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

    @RequestMapping("/list")
    public Map<String, List> employeeList() {
        Map<String, List> res = new HashMap<>();
//        Tuple2<List<Employee>, List<Article>> pair = service.findInfo();
//        res.put("employees", pair.getFirst());
//        res.put("articles", pair.getSecond());
        return res;
    }
}
