package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("")
    public DefaultDto.CreateResDto create(@RequestBody NoticeDto.CreateReqDto param){
        return noticeService.create(param);
    }
    @PutMapping("")
    public void update(@RequestBody NoticeDto.UpdateReqDto param){
        noticeService.update(param);
    }
    @DeleteMapping("")
    public void delete(@RequestBody NoticeDto.UpdateReqDto param){
        noticeService.delete(param.getId());
    }

    @GetMapping("/detail")
    public NoticeDto.DetailResDto detail(@RequestParam Long id){
        return noticeService.detail(id);
    }
    @GetMapping("/list")
    public List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param){
        return noticeService.list(param);
    }
    @GetMapping("/pagedList")
    public NoticeDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto param){
        return noticeService.pagedList(param);
    }
}
