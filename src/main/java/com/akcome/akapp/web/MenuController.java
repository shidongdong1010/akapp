package com.akcome.akapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单管理
 * Created by dongdongshi on 16/1/18.
 */
@Controller
public class MenuController {

    @RequestMapping("/menus")
    public String menus(){
        return "/menus/menus";
    }
}
