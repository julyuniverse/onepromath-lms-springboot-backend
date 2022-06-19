package com.onepromath.lms.dto.classes.classes;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseClassDto {
    private int schoolClassNo;
    private String className;
}
