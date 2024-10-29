package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Notice;
import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.mapper.NoticeMapper;
import com.thc.fallsprbasic.repository.NoticeRepository;
import com.thc.fallsprbasic.service.NoticeService;
import org.springframework.stereotype.Service;

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
    public DefaultDto.CreateResDto create(NoticeDto.CreateReqDto param) {
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
        notice.setDeleted(true);
        noticeRepository.save(notice);
        /*
        noticeRepository.delete(notice);
        */
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
    @Override
    public NoticeDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto param) {

        //총 등록수 예) 22개
        int countList = noticeMapper.pagedListCount(param);
        //요청 페이지 예) 3페이지
        int callpage = param.getCallpage();
        //요청 페이지가 1보다 작을때 1로 변환
        if(callpage < 1) { callpage = 1; }


        //한번에 볼 페이지수 예) 5개씩
        int perpage = param.getPerpage();
        int offset = (callpage - 1) * perpage;

        // 총 페이지수
        int countPage = (int) countList / perpage;
        if(countList % perpage > 0){
            countPage++;
        }
        //요청 페이지가 총 페이지수보다 클때 총 페이지수로 변환
        if(callpage > countPage) { callpage = countPage; }

        param.setOffset(offset);
        List<NoticeDto.DetailResDto> list = noticeMapper.pagedList(param);

        return NoticeDto.PagedListResDto.builder().countList(countList).callpage(callpage).countPage(countPage).list(list).build();
    }


}
