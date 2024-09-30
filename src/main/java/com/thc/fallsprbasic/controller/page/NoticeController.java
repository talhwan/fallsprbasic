package com.thc.fallsprbasic.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notice")
@Controller
public class NoticeController {

    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "notice/" + page;
    }

    @GetMapping("/{page}/{id}")
    public String page2(@PathVariable String page, @PathVariable String id){
        return "notice/" + page;
    }

}
