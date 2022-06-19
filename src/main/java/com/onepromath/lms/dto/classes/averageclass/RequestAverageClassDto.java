package com.onepromath.lms.dto.classes.averageclass;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestAverageClassDto {
    private int schoolInfoNo;
    private int schoolClassNo;
    private String startDate;
    private String endDate;
}
