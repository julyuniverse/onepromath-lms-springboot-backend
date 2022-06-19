package com.onepromath.lms.dto.student.weekly;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseWeeklyStudentDto {
    private int sequence; // 순번
    private int studentNo; // 학생 번호
    private String studentName; // 학생 이름
    private int learningTimeSeconds; // 학습 시간 (초)
    private int accuracy; // 정확도
    private int learningCount; // 학습량 (개수)
    private int mondayLearningCount; // 월요일 학습량 (개수)
    private int tuesdayLearningCount; // 화요일 학습량 (개수)
    private int wednesdayLearningCount; // 수요일 학습량 (개수)
    private int thursdayLearningCount; // 목요일 학습량 (개수)
    private int fridayLearningCount; // 금요일 학습량 (개수)
    private int saturdayLearningCount; // 토요일 학습량 (개수)
    private int sundayLearningCount; // 일요일 학습량 (개수)
}
