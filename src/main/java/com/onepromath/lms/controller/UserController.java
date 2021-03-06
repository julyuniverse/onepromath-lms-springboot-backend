package com.onepromath.lms.controller;

import com.onepromath.lms.dto.user.login.RequestUserLoginDto;
import com.onepromath.lms.dto.user.login.ResponseUserLoginDto;
import com.onepromath.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lms-backend") // 운영 단계에서는 apache에서 요청 주소를 www.example.com/api/lms-backend로 설정하기 때문에 해당 @RequestMapping은 주석 처리하거나 삭제하도록 한다.
public class UserController { // 유저
    private final UserService userService;

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<Optional<ResponseUserLoginDto>> login(@RequestBody RequestUserLoginDto requestUserLoginDto) {
        // 로그인 정보가 없다면 null 값 반환
        Optional<ResponseUserLoginDto> responseUserLoginDto = Optional.ofNullable(userService.login(requestUserLoginDto.getId(), requestUserLoginDto.getPw()));
        return ResponseEntity.ok().body(responseUserLoginDto);
    }
}
