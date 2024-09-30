package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.domain.Notice;
import com.thc.fallsprbasic.domain.User;
import com.thc.fallsprbasic.service.NoticeService;
import com.thc.fallsprbasic.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/notice")
@RestController
public class NoticeRestController {

    private final NoticeService noticeService;
    public NoticeRestController(
            NoticeService noticeService
    ) {
        this.noticeService = noticeService;
    }

    /**/

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params){
        return noticeService.create(params);
    }
    @GetMapping("/list")
    public List<Notice> list(){
        return noticeService.list();
    }
    @GetMapping("/detail")
    public Notice detail(@RequestParam Long id){
        return noticeService.detail(id);
    }
    @GetMapping("/update")
    public Map<String, Object> update(@RequestParam Map<String, Object> params){
        return noticeService.update(params);
    }
    @GetMapping("/delete")
    public Map<String, Object> delete(@RequestParam Map<String, Object> params){
        return noticeService.delete(Long.parseLong(params.get("id") + ""));
    }
}
