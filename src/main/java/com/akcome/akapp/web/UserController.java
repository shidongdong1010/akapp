package com.akcome.akapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dongdongshi on 16/1/13.
 */
@Controller
public class UserController {

    @RequestMapping("/users")
    public String users(){
        return "/users/users";
    }
}
