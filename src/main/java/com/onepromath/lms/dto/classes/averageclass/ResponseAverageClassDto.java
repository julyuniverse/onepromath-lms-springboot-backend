package com.onepromath.lms.dto.classes.averageclass;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseAverageClassDto {
    private float learningCount; // 학습량 (개수)
    private float accuracy; // 정확도
    private int learningTimeSeconds; // 학습 시간 (초)
}
