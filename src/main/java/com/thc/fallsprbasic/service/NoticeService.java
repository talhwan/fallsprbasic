package com.thc.fallsprbasic.service;

import com.thc.fallsprbasic.domain.Notice;
import com.thc.fallsprbasic.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NoticeService {
    /**/
    NoticeDto.CreateResDto create(NoticeDto.CreateReqDto param);
    void update(NoticeDto.UpdateReqDto param);
    Map<String, Object> delete(Long id);
    List<NoticeDto.DetailResDto> list();
    NoticeDto.DetailResDto detail(Long id);
}
