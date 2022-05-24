package com.onepromath.lms.dto.user.login;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestUserLoginDto { // 유저 로그인 요청 값
    private String id;
    private String pw;
}
