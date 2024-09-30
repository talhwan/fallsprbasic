package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Notice;
import com.thc.fallsprbasic.repository.NoticeRepository;
import com.thc.fallsprbasic.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    public NoticeServiceImpl(
            NoticeRepository noticeRepository
    ) {
        this.noticeRepository = noticeRepository;
    }

    /**/

    @Override
    public Map<String, Object> create(Map<String, Object> params) {
        System.out.println("create");
        Map<String, Object> result = new HashMap<String, Object>();

        Notice notice = new Notice();
        notice.setTitle(params.get("title").toString());
        notice.setContent(params.get("content").toString());
        noticeRepository.save(notice);

        result.put("success", true);
        result.put("id", notice.getId());
        return result;
    }
    @Override
    public Map<String, Object> update(Map<String, Object> params) {
        System.out.println("update");
        Notice notice = noticeRepository.findById(Long.parseLong(params.get("id") + "")).orElseThrow(() -> new RuntimeException(""));
        if(params.get("title") != null) {
            notice.setTitle(params.get("title").toString());
        }
        if(params.get("content") != null) {
            notice.setContent(params.get("content").toString());
        }
        noticeRepository.save(notice);
        return null;
    }
    @Override
    public List<Notice> list() {
        List<Notice> noticeList = noticeRepository.findAll();
        return noticeList;
    }
    @Override
    public Notice detail(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public Map<String, Object> delete(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        noticeRepository.delete(notice);
        return null;
    }
}
