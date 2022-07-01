package com.onepromath.lms.dto.learning.data.level;

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
public class ResponseLevelDataDto {
    private int id; // 레벨
    private int value; // 레벨별 학습량
    private float percent; // 퍼센트
    private String color; // 레벨별 색상
}
