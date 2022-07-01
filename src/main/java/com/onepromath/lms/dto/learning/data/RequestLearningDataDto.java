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
public class RequestLearningDataDto {
    private int studentNo; // 학생 번호
    private String startDate; // 시작일
    private String endDate; // 끝일
}
