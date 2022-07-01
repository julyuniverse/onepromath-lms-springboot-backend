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
public class ResponseLevelAndChapterDataDto {
    private int id; // 순번
    private int level; // 레벨
    private int chapter; // 챕터
    private String chapterName; // 챕터 이름
    private int value; // 학습량
    private float percent; // 퍼센트
    private String color; // 색상
}
