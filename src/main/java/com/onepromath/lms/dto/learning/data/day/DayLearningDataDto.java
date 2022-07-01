package com.onepromath.lms.dto.learning.data.day;

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
public class DayLearningDataDto {
    private String learningDate;
    private int dailyLearningCount;
    private int freeLearningCount;
    private int oneproLearningCount;
    private int worldLearningCount;
    private int learningCount;
    private float dailyAccuracy;
    private float freeAccuracy;
    private float oneproAccuracy;
    private float worldAccuracy;
    private float accuracy;
    private int dailyLearningTimeMinutes;
    private int freeLearningTimeMinutes;
    private int oneproLearningTimeMinutes;
    private int worldLearningTimeMinutes;
    private int learningTimeMinutes;
}
