package com.akcome.akapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by dongdongshi on 16/1/13.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/404")
    public String error404(){
        return "error-404";
    }

    @RequestMapping("/500")
    public String error500(){
        return "error-500";
    }
}
