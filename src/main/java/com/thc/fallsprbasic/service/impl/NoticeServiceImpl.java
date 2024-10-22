package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Notice;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.mapper.NoticeMapper;
import com.thc.fallsprbasic.repository.NoticeRepository;
import com.thc.fallsprbasic.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;
    public NoticeServiceImpl(
            NoticeRepository noticeRepository
            ,NoticeMapper noticeMapper
    ) {
        this.noticeRepository = noticeRepository;
        this.noticeMapper = noticeMapper;
    }

    /**/

    @Override
    public NoticeDto.CreateResDto create(NoticeDto.CreateReqDto param) {
        System.out.println("create");
        return noticeRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(NoticeDto.UpdateReqDto param) {
        System.out.println("update");
        Notice notice = noticeRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        if(param.getTitle() != null) {
            notice.setTitle(param.getTitle());
        }
        if(param.getContent() != null) {
            notice.setContent(param.getContent());
        }
        noticeRepository.save(notice);
    }
    @Override
    public Map<String, Object> delete(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        noticeRepository.delete(notice);
        return null;
    }

    @Override
    public NoticeDto.DetailResDto detail(Long id) {
        NoticeDto.DetailResDto returnVal = noticeMapper.detail(id);
        return returnVal;
    }

    @Override
    public List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param) {
        List<NoticeDto.DetailResDto> list = noticeMapper.list(param);
        return list;
    }


}
