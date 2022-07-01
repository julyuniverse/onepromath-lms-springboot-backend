package com.onepromath.lms.dto.learning.data.day;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 7/1/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestDailyLearningDataDto {
    private int studentNo;
    private String startDate;
    private String endDate;
}
