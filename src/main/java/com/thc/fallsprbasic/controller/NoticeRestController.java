package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.domain.Notice;
import com.thc.fallsprbasic.domain.User;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.service.NoticeService;
import com.thc.fallsprbasic.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    /*
    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params){
        return noticeService.create(params);
    }
    */
    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params){
        return null;
    }
    @GetMapping("/update")
    public Map<String, Object> update(@RequestParam Map<String, Object> params){
        return null;
    }

    @PostMapping("/create")
    public NoticeDto.CreateResDto create(@RequestBody NoticeDto.CreateReqDto param){
        return noticeService.create(param);
    }
    @PutMapping("/update")
    public void update(@RequestBody NoticeDto.UpdateReqDto param){
        noticeService.update(param);
    }

    @GetMapping("/list")
    public List<Notice> list(){
        return noticeService.list();
    }
    @GetMapping("/detail")
    public Notice detail(@RequestParam Long id){
        return noticeService.detail(id);
    }
    @GetMapping("/delete")
    public Map<String, Object> delete(@RequestParam Map<String, Object> params){
        return noticeService.delete(Long.parseLong(params.get("id") + ""));
    }
}
