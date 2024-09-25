package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Board;
import com.thc.fallsprbasic.domain.User;
import com.thc.fallsprbasic.repository.BoardRepository;
import com.thc.fallsprbasic.repository.UserRepository;
import com.thc.fallsprbasic.service.BoardService;
import com.thc.fallsprbasic.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, Object> login(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();

        String username = (String) params.get("username"); //사용자가 프론트에서 던진 아이디
        String password = (String) params.get("password"); //사용자가 프론트에서 던진 비밀번호

        /*
        //1번째 방법!!
        User user = userRepository.findByUsername(username); //사용자가 던진 아이디로 조회한 데이터베이스 담긴 진짜 사용자 정보
        if(user == null){
            //아이디도 틀린것 입니다.
            result.put("resultCode", 400);
            return result;
        }
        //아이디는 정상적인 것 입니다!!
        String password_real = user.getPassword();
        if(password_real.equals(password)){
            //사용자 입력값이랑 진짜 비번이랑 동일!! => 로그인 성공!!
            result.put("resultCode", 200);
            return result;
        } else {
            result.put("resultCode", 400);
            return result;
        }
         */

        //2번째 방법
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(user != null){
            //로그인 성공!!
            result.put("resultCode", 200);
            return result;
        }
        return null;

    }
    @Override
    public Map<String, Object> signup(Map<String, Object> params) {

        String username = (String) params.get("username");
        String password = (String) params.get("password");
        if(username == null || "".equals(username) || password == null || "".equals(password)){
            return null;
        }
        return create(params);
    }

    /**/

    @Override
    public Map<String, Object> create(Map<String, Object> params) {
        System.out.println("create");
        Map<String, Object> result = new HashMap<String, Object>();

        String username = (String) params.get("username");
        User user = userRepository.findByUsername(username);
        if(user == null){
            user = new User();
            user.setUsername((String) params.get("username"));
            user.setPassword((String) params.get("password"));
            user.setName((String) params.get("name"));
            user.setPhone((String) params.get("phone"));
            user = userRepository.save(user);

            result.put("id", user.getId());
        } else {
            result.put("id duplicated", user.getUsername());
        }
        return result;
    }
    @Override
    public Map<String, Object> update(Map<String, Object> params) {
        System.out.println("update");
        User user = userRepository.findById(Integer.parseInt(params.get("id") + "")).orElseThrow(() -> new RuntimeException(""));
        if(params.get("username") != null) {
            user.setUsername((String) params.get("username"));
        }
        if(params.get("password") != null) {
            user.setPassword((String) params.get("password"));
        }
        if(params.get("name") != null) {
            user.setName((String) params.get("name"));
        }
        if(params.get("phone") != null) {
            user.setPhone((String) params.get("phone"));
        }
        userRepository.save(user);
        return null;
    }
    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
    @Override
    public User detail(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }
    @Override
    public Map<String, Object> delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        userRepository.delete(user);
        return null;
    }
}
