package com.onepromath.lms.dto.learning.data;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 6/27/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseLearningDataDto {
    private int level; // 레벨
    private int chapter; // 챕터
    private int unit; // 유닛
    private String unitName; // 유닛 이름(스테이지 이름)
    private String learningMode; // 학습 모드
    private int learningTimeSeconds; // 학습 시간 (초)
    private int grade; // 등급
    private float accuracy; // 정확도
    private String month; // 월
    private String day; // 일
    private String startLearningTime; // 학습 시작 시간
}
