package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.controller.request.SearchRequest;
import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Controller
@RequestMapping(value = "/")
public class TopController {

    @Autowired
    private TagService tagService;

    @ModelAttribute("keyword")
    public SearchRequest setUpForm() {
        return new SearchRequest();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "index";
    }
}
