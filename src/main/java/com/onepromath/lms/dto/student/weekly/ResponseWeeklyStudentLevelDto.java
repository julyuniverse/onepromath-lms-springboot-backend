package com.onepromath.lms.dto.student.weekly;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseWeeklyStudentLevelDto {
    private int level;
    private int chapter;
    private int learningCount;
    private int schoolYear;
}
