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
public class ResponseLastWeekAndThisWeekLearningDataDto {
    private String lastWeekLearningDate;
    private int lastWeekDailyLearningCount;
    private int lastWeekFreeLearningCount;
    private int lastWeekOneproLearningCount;
    private int lastWeekWorldLearningCount;
    private int lastWeekLearningCount;
    private float lastWeekDailyAccuracy;
    private float lastWeekFreeAccuracy;
    private float lastWeekOneproAccuracy;
    private float lastWeekWorldAccuracy;
    private float lastWeekAccuracy;
    private int lastWeekDailyLearningTimeMinutes;
    private int lastWeekFreeLearningTimeMinutes;
    private int lastWeekOneproLearningTimeMinutes;
    private int lastWeekWorldLearningTimeMinutes;
    private int lastWeekLearningTimeMinutes;
    private String thisWeekLearningDate;
    private int thisWeekDailyLearningCount;
    private int thisWeekFreeLearningCount;
    private int thisWeekOneproLearningCount;
    private int thisWeekWorldLearningCount;
    private int thisWeekLearningCount;
    private float thisWeekDailyAccuracy;
    private float thisWeekFreeAccuracy;
    private float thisWeekOneproAccuracy;
    private float thisWeekWorldAccuracy;
    private float thisWeekAccuracy;
    private int thisWeekDailyLearningTimeMinutes;
    private int thisWeekFreeLearningTimeMinutes;
    private int thisWeekOneproLearningTimeMinutes;
    private int thisWeekWorldLearningTimeMinutes;
    private int thisWeekLearningTimeMinutes;
}
