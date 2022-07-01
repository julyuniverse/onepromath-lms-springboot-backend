package com.onepromath.lms.dto.learning.data.week;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 6/30/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestLastWeekAndThisWeekLearningDataDto {
    private int studentNo;
    private String startDate;
}
