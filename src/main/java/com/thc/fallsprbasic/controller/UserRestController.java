package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.domain.Board;
import com.thc.fallsprbasic.domain.User;
import com.thc.fallsprbasic.dto.UserDto;
import com.thc.fallsprbasic.service.BoardService;
import com.thc.fallsprbasic.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/user")
@RestController
public class UserRestController {

    private final UserService userService;
    public UserRestController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDto.LoginResDto login(@RequestBody UserDto.LoginReqDto param){
        return userService.login(param);
    }

    @PostMapping("/signup")
    public UserDto.CreateResDto signup(@RequestBody UserDto.CreateReqDto param){
        return userService.signup(param);
    }
    @GetMapping("/id")
    public boolean id(@RequestParam String username){
        return userService.id(username);
    }

    /**/

    @PostMapping("/create")
    public UserDto.CreateResDto create(@RequestBody UserDto.CreateReqDto param){
        return userService.create(param);
    }
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }
    @GetMapping("/detail")
    public User detail(@RequestParam Long id){
        return userService.detail(id);
    }
    @GetMapping("/update")
    public Map<String, Object> update(@RequestParam Map<String, Object> params){
        return userService.update(params);
    }
    @GetMapping("/delete")
    public Map<String, Object> delete(@RequestParam Map<String, Object> params){
        return userService.delete(Long.parseLong(params.get("id") + ""));
    }
}
