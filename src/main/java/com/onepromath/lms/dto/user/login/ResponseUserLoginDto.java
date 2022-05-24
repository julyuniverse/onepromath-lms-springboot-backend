package com.onepromath.lms.dto.user.login;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseUserLoginDto { // 유저 로그인 응답 값
    private String teacherName; // 선생님 이름
    private int schoolInfoNo; // SCHOOL_INFO->NO
    private String schoolName; // 학교||학원 이름
}
