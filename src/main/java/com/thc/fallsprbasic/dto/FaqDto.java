package com.thc.fallsprbasic.dto;

import com.thc.fallsprbasic.domain.Faq;
import com.thc.fallsprbasic.domain.Notice;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class FaqDto {

    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class CreateReqDto extends DefaultDto.CreateReqDto {
        private Long userId;

        private String title;
        private String content;

        public Faq toEntity(){
            return Faq.of(getUserId(), getTitle(), getContent());
        }
    }
    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        private String title;
        private String content;
    }

    @AllArgsConstructor @NoArgsConstructor @Setter @Getter
    public static class DetailResDto extends DefaultDto.DetailResDto {
        private Long userId;
        private String title;
        private String content;
        private String userUsername;
    }

    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class ListReqDto extends DefaultDto.ListReqDto {
        private String title;
    }

    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        private String title;
    }
    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        private String title;
    }

}
