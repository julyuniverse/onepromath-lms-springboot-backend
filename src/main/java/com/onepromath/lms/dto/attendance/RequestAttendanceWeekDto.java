package com.onepromath.lms.dto.attendance;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 6/29/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RequestAttendanceWeekDto {
    private int studentNo; // 학생 번호
    private String startDate; // 시작일
}
