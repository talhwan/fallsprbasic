package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.service.NoticeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<DefaultDto.CreateResDto> create(
            @RequestPart NoticeDto.CreateReqDto param
            ,@RequestPart(required = false) MultipartFile imgfile
    ){
        if(imgfile !=null && !imgfile.isEmpty()){param.setImgfile(imgfile);}
        return ResponseEntity.ok(noticeService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody NoticeDto.UpdateReqDto param){
        noticeService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody NoticeDto.UpdateReqDto param){
        noticeService.delete(param.getId());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/list")
    public ResponseEntity<Void> deletes(@RequestBody DefaultDto.DeletesReqDto param){
        noticeService.deletes(param);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<NoticeDto.DetailResDto> detail(@RequestParam Long id){
        return ResponseEntity.ok(noticeService.detail(id));
    }
    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto.DetailResDto>> list(NoticeDto.ListReqDto param){
        return ResponseEntity.ok(noticeService.list(param));
    }
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(NoticeDto.PagedListReqDto param){
        return ResponseEntity.ok(noticeService.pagedList(param));
    }
    @GetMapping("/mlist")
    public ResponseEntity<List<NoticeDto.DetailResDto>> mlist(NoticeDto.ScrollListReqDto param){
        return ResponseEntity.ok(noticeService.scrollList(param));
    }
}
