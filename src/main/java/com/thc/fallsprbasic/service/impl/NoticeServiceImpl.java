package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Notice;
import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.mapper.NoticeMapper;
import com.thc.fallsprbasic.repository.NoticeRepository;
import com.thc.fallsprbasic.service.NoticeService;
import com.thc.fallsprbasic.util.FileUpload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository userRepository;
    private final NoticeMapper userMapper;
    public NoticeServiceImpl(
            NoticeRepository userRepository
            , NoticeMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(NoticeDto.CreateReqDto param) {
        System.out.println("create");
        param.setImg(FileUpload.upload(param.getImgfile()));
        return userRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(NoticeDto.UpdateReqDto param) {
        System.out.println("update");
        Notice user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        if(param.getDeleted() != null) {
            user.setDeleted(param.getDeleted());
        }
        if(param.getTitle() != null) {
            user.setTitle(param.getTitle());
        }
        if(param.getContent() != null) {
            user.setContent(param.getContent());
        }
        if(param.getImg() != null) {
            user.setImg(param.getImg());
        }
        userRepository.save(user);
    }
    @Override
    public void delete(Long id) {
        update(NoticeDto.UpdateReqDto.builder().id(id).deleted(true).build());
    }
    @Override
    public void deletes(DefaultDto.DeletesReqDto param) {
        for(Long id : param.getIds()){
            delete(id);
        }
    }

    public NoticeDto.DetailResDto get(Long id) {
        return userMapper.detail(id);
    }
    public List<NoticeDto.DetailResDto> detailList(List<NoticeDto.DetailResDto> list) {
        List<NoticeDto.DetailResDto> newList = new ArrayList<>();
        for(NoticeDto.DetailResDto each : list) {
            newList.add(get(each.getId()));
        }
        return newList;
    }
    @Override
    public NoticeDto.DetailResDto detail(Long id) {
        return get(id);
    }
    @Override
    public List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param) {
        return detailList(userMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto param){
        DefaultDto.PagedListResDto retrunVal = DefaultDto.PagedListResDto.init(param, userMapper.pagedListCount(param));
        retrunVal.setList(detailList(userMapper.pagedList(param)));
        return retrunVal;
    }
    @Override
    public List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto param){
        param.init();
        Long cursor = param.getCursor();
        if(cursor != null){
            Notice user = userRepository.findById(cursor).orElseThrow(() -> new RuntimeException(""));
            param.setCreatedAt(user.getCreatedAt() + "");
        }
        return detailList(userMapper.scrollList(param));
    }

}
