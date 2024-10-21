package com.thc.fallsprbasic.mapper;

import com.thc.fallsprbasic.dto.FaqDto;

import java.util.List;

public interface FaqMapper {
	/**/
	FaqDto.DetailResDto detail(Long id);
	List<FaqDto.DetailResDto> list(FaqDto.ListReqDto param);
}