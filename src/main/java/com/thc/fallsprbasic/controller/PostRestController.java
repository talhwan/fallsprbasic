package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.dto.PostDto;
import com.thc.fallsprbasic.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/post")
@RestController
public class PostRestController {

    private final PostService postService;
    public PostRestController(
            PostService postService
    ) {
        this.postService = postService;
    }

    /**/

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(
            @RequestPart PostDto.CreateReqDto param
            ,@RequestPart(required = false) List<MultipartFile> imgfile
    ){
        if(imgfile != null && !imgfile.isEmpty()){param.setImgs(imgfile);}
        return ResponseEntity.ok(postService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostDto.UpdateReqDto param){
        postService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostDto.UpdateReqDto param){
        postService.delete(param.getId());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/list")
    public ResponseEntity<Void> deletes(@RequestBody DefaultDto.DeletesReqDto param){
        postService.deletes(param);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<PostDto.DetailResDto> detail(@RequestParam Long id){
        return ResponseEntity.ok(postService.detail(id));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostDto.DetailResDto>> list(PostDto.ListReqDto param){
        return ResponseEntity.ok(postService.list(param));
    }
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(PostDto.PagedListReqDto param){
        return ResponseEntity.ok(postService.pagedList(param));
    }
    @GetMapping("/mlist")
    public ResponseEntity<List<PostDto.DetailResDto>> mlist(PostDto.ScrollListReqDto param){
        return ResponseEntity.ok(postService.scrollList(param));
    }
}
