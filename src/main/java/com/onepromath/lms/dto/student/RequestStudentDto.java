package com.onepromath.lms.dto.student;

import lombok.*;

/**
 * @author Lee Taesung
 * @since 7/4/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestStudentDto {
    private int schoolNo;
    private int classNo;
}
