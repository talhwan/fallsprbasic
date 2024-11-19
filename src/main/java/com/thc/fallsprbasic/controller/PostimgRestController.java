package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.PostimgDto;
import com.thc.fallsprbasic.service.PostimgService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/postimg")
@RestController
public class PostimgRestController {

    private final PostimgService postimgService;
    public PostimgRestController(
            PostimgService postimgService
    ) {
        this.postimgService = postimgService;
    }

    /**/

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(
            @RequestPart PostimgDto.CreateReqDto param
            ,@RequestPart(required = false) MultipartFile imgfile
    ){
        if(imgfile !=null && !imgfile.isEmpty()){param.setImgfile(imgfile);}
        return ResponseEntity.ok(postimgService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostimgDto.UpdateReqDto param){
        postimgService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostimgDto.UpdateReqDto param){
        postimgService.delete(param.getId());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/list")
    public ResponseEntity<Void> deletes(@RequestBody DefaultDto.DeletesReqDto param){
        postimgService.deletes(param);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<PostimgDto.DetailResDto> detail(@RequestParam Long id){
        return ResponseEntity.ok(postimgService.detail(id));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostimgDto.DetailResDto>> list(PostimgDto.ListReqDto param){
        return ResponseEntity.ok(postimgService.list(param));
    }
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(PostimgDto.PagedListReqDto param){
        return ResponseEntity.ok(postimgService.pagedList(param));
    }
    @GetMapping("/mlist")
    public ResponseEntity<List<PostimgDto.DetailResDto>> mlist(PostimgDto.ScrollListReqDto param){
        return ResponseEntity.ok(postimgService.scrollList(param));
    }
}
