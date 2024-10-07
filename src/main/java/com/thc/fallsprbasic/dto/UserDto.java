package com.thc.fallsprbasic.dto;

import com.thc.fallsprbasic.domain.User;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Setter
    @Getter
    public static class CreateReqDto {
        private String username;
        private String password;
        private String name;
        private String phone;

        public User toEntity() {
            User user = new User();
            user.setUsername(getUsername());
            user.setPassword(getPassword());
            user.setName(getName());
            user.setPhone(getPhone());
            return user;
        }
    }
    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
    @Setter
    @Getter
    public static class LoginResDto {
        private boolean result;
    }

    @Setter
    @Getter
    public static class UpdateReqDto {
        private Long id;
        private String name;
        private String phone;
    }

    @Setter
    @Getter
    public static class CreateResDto {
        private Long id;
    }

}
