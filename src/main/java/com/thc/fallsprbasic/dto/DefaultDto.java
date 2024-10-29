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
        private int countList;
        private int countPage;
        private int callpage;
        private Object list;
    }

}
