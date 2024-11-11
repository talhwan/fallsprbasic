package com.thc.fallsprbasic.dto;

import com.thc.fallsprbasic.domain.User;
import com.thc.fallsprbasic.domain.Notice;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class UserDto {

    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class LoginReqDto{
        private String username;
        private String password;
    }

    /**/
    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class CreateReqDto extends DefaultDto.CreateReqDto {
        private String username;
        private String password;
        private String name;
        private String phone;

        public User toEntity(){
            return User.of(getUsername(), getPassword(), getName(), getPhone());
        }
    }
    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        private String password;
        private String name;
        private String phone;
    }

    @AllArgsConstructor @NoArgsConstructor @Setter @Getter
    public static class DetailResDto extends DefaultDto.DetailResDto {
        private String username;
        private String name;
        private String phone;
    }

    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class ListReqDto extends DefaultDto.ListReqDto {
        private String name;
        private String phone;
    }

    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        private String name;
        private String phone;
    }
    @AllArgsConstructor @NoArgsConstructor @SuperBuilder @Setter @Getter
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        private String name;
        private String phone;
    }

}
