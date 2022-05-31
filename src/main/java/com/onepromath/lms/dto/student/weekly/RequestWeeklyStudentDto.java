package com.onepromath.lms.dto.student.weekly;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestWeeklyStudentDto {
    private int schoolInfoNo;
    private int schoolClassNo;
    private String startDate;
    private String endDate;
}
