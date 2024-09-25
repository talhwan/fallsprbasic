package com.thc.fallsprbasic.controller;

import com.thc.fallsprbasic.domain.Board;
import com.thc.fallsprbasic.domain.User;
import com.thc.fallsprbasic.service.BoardService;
import com.thc.fallsprbasic.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam Map<String, Object> params){
        return userService.login(params);
    }

    @GetMapping("/signup")
    public Map<String, Object> signup(@RequestParam Map<String, Object> params){
        return userService.signup(params);
    }

    /**/

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params){
        return userService.create(params);
    }
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }
    @GetMapping("/detail")
    public User detail(@RequestParam Integer id){
        return userService.detail(id);
    }
    @GetMapping("/update")
    public Map<String, Object> update(@RequestParam Map<String, Object> params){
        return userService.update(params);
    }
    @GetMapping("/delete")
    public Map<String, Object> delete(@RequestParam Map<String, Object> params){
        return userService.delete(Integer.parseInt(params.get("id") + ""));
    }
}
