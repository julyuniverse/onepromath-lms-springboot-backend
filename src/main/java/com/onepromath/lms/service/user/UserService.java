package com.onepromath.lms.service.user;

import com.onepromath.lms.dto.user.login.ResponseUserLoginDto;
import com.onepromath.lms.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService { // 유저
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 로그인
    public ResponseUserLoginDto login(String id, String pw) {
        ResponseUserLoginDto responseUserLoginDto = userMapper.login(id, pw);

        return responseUserLoginDto;
    }
}
