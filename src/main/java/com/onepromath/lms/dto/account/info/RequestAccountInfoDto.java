package com.onepromath.lms.dto.account.info;

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
public class RequestAccountInfoDto {
    private int userNo;
    private int profileNo;
}
