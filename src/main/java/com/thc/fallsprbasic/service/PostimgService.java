package com.thc.fallsprbasic.service;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.PostimgDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostimgService {
    /**/
    PostimgDto.CreateResDto create(PostimgDto.CreateReqDto param);
    void update(PostimgDto.UpdateReqDto param);
    void delete(Long id);
    void deletes(DefaultDto.DeletesReqDto param);
    PostimgDto.DetailResDto detail(Long id);
    List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PostimgDto.PagedListReqDto param);
    List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto param);

}
