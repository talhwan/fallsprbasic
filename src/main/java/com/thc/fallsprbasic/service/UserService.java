package com.thc.fallsprbasic.service;

import com.thc.fallsprbasic.domain.User;
import com.thc.fallsprbasic.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    UserDto.LoginResDto login(UserDto.LoginReqDto param);
    UserDto.CreateResDto signup(UserDto.CreateReqDto param);
    boolean id(String username);
    /**/
    UserDto.CreateResDto create(UserDto.CreateReqDto params);
    Map<String, Object> update(Map<String, Object> params);
    Map<String, Object> delete(Long id);
    List<User> list();
    User detail(Long id);
}
