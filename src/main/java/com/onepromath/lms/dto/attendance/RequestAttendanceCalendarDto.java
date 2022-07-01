package com.onepromath.lms.dto.attendance;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestAttendanceCalendarDto {
    private int studentNo; // 학생 번호
    private String startDate; // 시작일
}
