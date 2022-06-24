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
public class ResponseWeeklyLearningDataDto {
    private int dailyModeCount;
    private int freeModeCount;
    private int oneproModeCount;
    private int worldModeCount;
    private int learningCount;
    private float dailyAccuracy;
    private float freeAccuracy;
    private float oneproAccuracy;
    private float worldAccuracy;
    private float accuracy;
    private int dailyLearningTimeSeconds;
    private int freeLearningTimeSeconds;
    private int oneproLearningTimeSeconds;
    private int worldLearningTimeSeconds;
    private int learningTimeSeconds;
    private int dailyLearningTimeMinutes;
    private int freeLearningTimeMinutes;
    private int oneproLearningTimeMinutes;
    private int worldLearningTimeMinutes;
    private int learningTimeMinutes;
    private int problemCount;
    private String date;
    private String year;
    private String month;
}
