package com.onepromath.lms.dto.attendance.calendar;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestCalendarDto {
    private int studentNo;
    private String startDate;
}
