package com.onepromath.lms.dto.learning.data.month;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 6/23/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestMonthlyLearningDataDto {
    private int studentNo; // 학생 번호
    private String startDate; // 해당 월 시작일
    private int count; // 개월 수
}
