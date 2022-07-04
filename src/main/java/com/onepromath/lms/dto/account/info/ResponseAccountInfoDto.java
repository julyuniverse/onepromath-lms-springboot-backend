package com.onepromath.lms.dto.account.info;

import lombok.*;

import java.util.ArrayList;

/**
 * @author Lee Taesung
 * @since 7/4/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseAccountInfoDto {
    private int existenceStatus; // 존재 여부
    private int userNo; // 유저 넘버
    private int useStatus; // 사용 여부
    private int paidAccountStatus; // 결제 계정 여부
    private int paidStatus; // 결제 여부
    private int schoolAccountStatus; // 학교 계정 여부
    ArrayList<ProfileInfoDto> profiles;
}
