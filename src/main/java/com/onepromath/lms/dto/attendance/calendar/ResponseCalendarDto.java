package com.onepromath.lms.dto.attendance.calendar;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResponseCalendarDto {
    private int sequence;
    private String learningDate;
    private int day;
    private boolean attendanceStatus;
    private int learningCount;
    private int totalCount;
    private int rightCount;
    private int learningTimeSeconds;
    private int dailyModeCount;
    private int freeModeCount;
    private int oneproModeCount;
    private int worldModeCount;
}
