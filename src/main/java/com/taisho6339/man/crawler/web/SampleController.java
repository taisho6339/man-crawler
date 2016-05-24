package com.taisho6339.man.crawler.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by taisho6339 on 16/05/25.
 */
@Controller
public class SampleController {
    @RequestMapping(path = "/")
    public ModelAndView welcomeHandler() {
        String message = "Hello World.";
        return new ModelAndView("index", "message", message);
    }
}
