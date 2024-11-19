package com.thc.fallsprbasic.mapper;

import com.thc.fallsprbasic.dto.PostimgDto;

import java.util.List;

public interface PostimgMapper {
	PostimgDto.DetailResDto detail(Long id);
	List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto param);
	List<PostimgDto.DetailResDto> pagedList(PostimgDto.PagedListReqDto param);
	int pagedListCount(PostimgDto.PagedListReqDto param);
	List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto param);
}