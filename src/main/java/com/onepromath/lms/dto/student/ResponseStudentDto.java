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
public class ResponseStudentDto {
    private int studentNo;
    private String studentName;
}
