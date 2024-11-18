package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Post;
import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.PostDto;
import com.thc.fallsprbasic.mapper.PostMapper;
import com.thc.fallsprbasic.repository.PostRepository;
import com.thc.fallsprbasic.service.PostService;
import com.thc.fallsprbasic.util.FileUpload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository userRepository;
    private final PostMapper userMapper;
    public PostServiceImpl(
            PostRepository userRepository
            , PostMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(PostDto.CreateReqDto param) {
        System.out.println("create");
        return userRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(PostDto.UpdateReqDto param) {
        System.out.println("update");
        Post user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        if(param.getDeleted() != null) {
            user.setDeleted(param.getDeleted());
        }
        if(param.getTitle() != null) {
            user.setTitle(param.getTitle());
        }
        if(param.getContent() != null) {
            user.setContent(param.getContent());
        }
        userRepository.save(user);
    }
    @Override
    public void delete(Long id) {
        update(PostDto.UpdateReqDto.builder().id(id).deleted(true).build());
    }
    @Override
    public void deletes(DefaultDto.DeletesReqDto param) {
        for(Long id : param.getIds()){
            delete(id);
        }
    }

    public PostDto.DetailResDto get(Long id) {
        return userMapper.detail(id);
    }
    public List<PostDto.DetailResDto> detailList(List<PostDto.DetailResDto> list) {
        List<PostDto.DetailResDto> newList = new ArrayList<>();
        for(PostDto.DetailResDto each : list) {
            newList.add(get(each.getId()));
        }
        return newList;
    }
    @Override
    public PostDto.DetailResDto detail(Long id) {
        return get(id);
    }
    @Override
    public List<PostDto.DetailResDto> list(PostDto.ListReqDto param) {
        return detailList(userMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PostDto.PagedListReqDto param){
        DefaultDto.PagedListResDto retrunVal = DefaultDto.PagedListResDto.init(param, userMapper.pagedListCount(param));
        retrunVal.setList(detailList(userMapper.pagedList(param)));
        return retrunVal;
    }
    @Override
    public List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto param){
        param.init();
        Long cursor = param.getCursor();
        if(cursor != null){
            Post user = userRepository.findById(cursor).orElseThrow(() -> new RuntimeException(""));
            param.setCreatedAt(user.getCreatedAt() + "");
        }
        return detailList(userMapper.scrollList(param));
    }

}
