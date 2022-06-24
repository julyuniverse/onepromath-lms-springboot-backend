package com.onepromath.lms.dto.learning.data.week;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 6/24/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestWeeklyLearningDataDto {
    private int studentNo;
    private String startDate;
}
