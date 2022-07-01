package com.onepromath.lms.dto.attendance;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 6/29/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseAttendanceWeekDto {
    private String learningDate;
    private int day;
    private String dayOfWeek;
    private boolean attendanceStatus;
    private int learningCount;
    private int totalCount;
    private int rightCount;
    private int learningTimeSeconds;
}
