package com.taisho6339.man.crawler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by taisho6339 on 16/05/25.
 */
//@RestController
@Controller
public class SampleController {

    @RequestMapping(path = "/")
    public String get() {
//        return "Hello World";
        return "index";
    }
}
