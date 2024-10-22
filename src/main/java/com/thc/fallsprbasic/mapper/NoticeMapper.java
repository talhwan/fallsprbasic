package com.thc.fallsprbasic.mapper;

import com.thc.fallsprbasic.dto.NoticeDto;
import java.util.List;

public interface NoticeMapper {
	/**/
	NoticeDto.DetailResDto detail(Long id);
	List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param);
}