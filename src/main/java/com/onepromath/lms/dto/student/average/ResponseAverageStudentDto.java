package com.onepromath.lms.dto.student.average;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseAverageStudentDto {
    private int studentNo; // 학생 번호
    private String studentName; // 학생 이름
    private int learningCount; // 학습량 (개수)
    private float accuracy; // 정확도
    private int learningTimeSeconds; // 학습 시간 (초)
}
