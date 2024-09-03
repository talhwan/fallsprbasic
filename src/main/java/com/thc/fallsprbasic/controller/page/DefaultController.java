package com.thc.fallsprbasic.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class DefaultController {
    @GetMapping("/index") //이 안에 있는 주소값은 꼭 유니크해야함!!
    public String index(){
        return "index";
    }
}
