package com.thc.fallsprbasic.mapper;

import com.thc.fallsprbasic.dto.FaqDto;
import java.util.List;

public interface FaqMapper {
	/**/
	FaqDto.DetailResDto detail(Long id);
	List<FaqDto.DetailResDto> list(FaqDto.ListReqDto param);
	List<FaqDto.DetailResDto> pagedList(FaqDto.PagedListReqDto param);
	int pagedListCount(FaqDto.PagedListReqDto param);
	List<FaqDto.DetailResDto> scrollList(FaqDto.ScrollListReqDto param);
}