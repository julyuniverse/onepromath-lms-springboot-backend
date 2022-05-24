package com.onepromath.lms.dto.student;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseStudentDto {
    private int studentNo;
    private String studentName;
}
