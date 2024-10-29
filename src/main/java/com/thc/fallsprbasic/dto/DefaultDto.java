package com.thc.fallsprbasic.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class DefaultDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Setter
    @Getter
    public static class CreateReqDto {
        private Boolean deleted;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Setter
    @Getter
    public static class UpdateReqDto {
        private Long id;
        private Boolean deleted;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Setter
    @Getter
    public static class CreateResDto {
        private Long id;
    }

    //여기는 빌더 사용 금지!!
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class DetailResDto {
        private Long id;
        private Boolean deleted;
        private LocalDateTime createdAt;
        private String createdAtDateTime;
        private LocalDateTime modifiedAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Setter
    @Getter
    public static class ListReqDto {
        private Boolean deleted;
    }

    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class PagedListReqDto {
        private int callpage; // 요청 페이지
        private String orderby; //정렬 기준
        private String orderway; //정렬 방향

        private Integer perpage; //한페이지에 몇개 보여줄지
        private Integer offset; //몇번째 정보부터 보여줄지
    }
    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class PagedListResDto {
        private int callpage;
        private int itemcount;
        private int pagecount;
        private Object list;

        public static PagedListResDto init(PagedListReqDto param, int itemcount){
            //offset 을 구하기 위함!!
            Integer perpage = param.getPerpage();
            if(perpage == null){
                perpage = 10;
            }
            int pagecount = itemcount / perpage;
            if(itemcount % perpage > 0){
                pagecount++;
            }
            int callpage = param.getCallpage();
            if(callpage < 1){ callpage = 1; }
            if(callpage > pagecount){ callpage = pagecount; }
            int offset = (callpage - 1) * perpage;
            param.setOffset(offset);

            //정렬 기준
            String orderby = param.getOrderby();
            if(orderby == null || orderby.isEmpty()){
                orderby = "created_at";
            }
            param.setOrderby(orderby);

            //정렬 방향
            String orderway = param.getOrderway();
            if(orderway == null || orderway.isEmpty()){
                orderway = "desc";
            }
            param.setOrderway(orderway);

            return DefaultDto.PagedListResDto.builder()
                    .itemcount(itemcount)
                    .pagecount(pagecount)
                    .callpage(callpage)
                    .build();
        }
    }

}
