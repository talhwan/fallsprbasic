package com.thc.fallsprbasic.service;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NoticeService {
    /**/
    DefaultDto.CreateResDto create(NoticeDto.CreateReqDto param);
    void update(NoticeDto.UpdateReqDto param);
    Map<String, Object> delete(Long id);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param);
    NoticeDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto param);
    NoticeDto.DetailResDto detail(Long id);
}
