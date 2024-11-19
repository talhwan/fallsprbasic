package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Postimg;
import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.PostimgDto;
import com.thc.fallsprbasic.mapper.PostimgMapper;
import com.thc.fallsprbasic.repository.PostimgRepository;
import com.thc.fallsprbasic.service.PostimgService;
import com.thc.fallsprbasic.util.FileUpload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostimgServiceImpl implements PostimgService {

    private final PostimgRepository userRepository;
    private final PostimgMapper userMapper;
    public PostimgServiceImpl(
            PostimgRepository userRepository
            , PostimgMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**/

    @Override
    public PostimgDto.CreateResDto create(PostimgDto.CreateReqDto param) {
        System.out.println("create");
        String url = FileUpload.upload(param.getImgfile());
        if(url == null){
            return PostimgDto.CreateResDto.builder().id((long) -200).build();
        }
        param.setUrl(url);
        return userRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(PostimgDto.UpdateReqDto param) {
        System.out.println("update");
        Postimg user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        if(param.getDeleted() != null) {
            user.setDeleted(param.getDeleted());
        }
        userRepository.save(user);
    }
    @Override
    public void delete(Long id) {
        update(PostimgDto.UpdateReqDto.builder().id(id).deleted(true).build());
    }
    @Override
    public void deletes(DefaultDto.DeletesReqDto param) {
        for(Long id : param.getIds()){
            delete(id);
        }
    }

    public PostimgDto.DetailResDto get(Long id) {
        return userMapper.detail(id);
    }
    public List<PostimgDto.DetailResDto> detailList(List<PostimgDto.DetailResDto> list) {
        List<PostimgDto.DetailResDto> newList = new ArrayList<>();
        for(PostimgDto.DetailResDto each : list) {
            newList.add(get(each.getId()));
        }
        return newList;
    }
    @Override
    public PostimgDto.DetailResDto detail(Long id) {
        return get(id);
    }
    @Override
    public List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto param) {
        return detailList(userMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PostimgDto.PagedListReqDto param){
        DefaultDto.PagedListResDto retrunVal = DefaultDto.PagedListResDto.init(param, userMapper.pagedListCount(param));
        retrunVal.setList(detailList(userMapper.pagedList(param)));
        return retrunVal;
    }
    @Override
    public List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto param){
        param.init();
        Long cursor = param.getCursor();
        if(cursor != null){
            Postimg user = userRepository.findById(cursor).orElseThrow(() -> new RuntimeException(""));
            param.setCreatedAt(user.getCreatedAt() + "");
        }
        return detailList(userMapper.scrollList(param));
    }

}
