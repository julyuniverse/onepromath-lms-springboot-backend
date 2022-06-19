package com.onepromath.lms.dto.student.average;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestAverageStudentDto {
    private int schoolInfoNo;
    private int schoolClassNo;
    private String startDate;
    private String endDate;
    private int sort;
    private boolean order;
}
