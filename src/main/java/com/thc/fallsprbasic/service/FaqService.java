package com.thc.fallsprbasic.service;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.FaqDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FaqService {
    /**/
    DefaultDto.CreateResDto create(FaqDto.CreateReqDto param);
    void update(FaqDto.UpdateReqDto param);
    void delete(Long id);
    FaqDto.DetailResDto detail(Long id);
    List<FaqDto.DetailResDto> list(FaqDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(FaqDto.PagedListReqDto param);
    List<FaqDto.DetailResDto> scrollList(FaqDto.ScrollListReqDto param);
}
