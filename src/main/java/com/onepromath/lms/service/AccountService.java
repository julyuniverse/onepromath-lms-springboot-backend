package com.onepromath.lms.service;

import com.onepromath.lms.dto.account.info.ProfileInfoDto;
import com.onepromath.lms.dto.account.info.ResponseAccountInfoDto;
import com.onepromath.lms.dto.account.info.UserInfoDto;
import com.onepromath.lms.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author Lee Taesung
 * @since 7/4/22
 */

@Service
@Transactional
public class AccountService {
    private final AccountMapper accountMapper;

    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    // 계정 정보
    public ResponseAccountInfoDto accountInfo(int userNo) {
        UserInfoDto userInfoDto = accountMapper.userInfo(userNo);

        ArrayList<ProfileInfoDto> profileInfoDto = accountMapper.profileInfo(userNo);

        ResponseAccountInfoDto responseAccountInfoDto = new ResponseAccountInfoDto();
        responseAccountInfoDto.setExistenceStatus(userInfoDto.getExistenceStatus());
        responseAccountInfoDto.setUserNo(userInfoDto.getUserNo());
        responseAccountInfoDto.setUseStatus(userInfoDto.getUseStatus());
        responseAccountInfoDto.setPaidAccountStatus(userInfoDto.getPaidAccountStatus());
        responseAccountInfoDto.setPaidStatus(userInfoDto.getPaidStatus());
        responseAccountInfoDto.setSchoolAccountStatus(userInfoDto.getSchoolAccountStatus());
        responseAccountInfoDto.setProfiles(profileInfoDto);

        return responseAccountInfoDto;
    }

    // 계정 정보2
    public ResponseAccountInfoDto accountInfo2(int profileNo) {
        UserInfoDto userInfoDto = accountMapper.userInfo2(profileNo);

        ArrayList<ProfileInfoDto> profileInfoDto = accountMapper.profileInfo(userInfoDto.getUserNo());

        ResponseAccountInfoDto responseAccountInfoDto = new ResponseAccountInfoDto();
        responseAccountInfoDto.setExistenceStatus(userInfoDto.getExistenceStatus());
        responseAccountInfoDto.setUserNo(userInfoDto.getUserNo());
        responseAccountInfoDto.setUseStatus(userInfoDto.getUseStatus());
        responseAccountInfoDto.setPaidAccountStatus(userInfoDto.getPaidAccountStatus());
        responseAccountInfoDto.setPaidStatus(userInfoDto.getPaidStatus());
        responseAccountInfoDto.setSchoolAccountStatus(userInfoDto.getSchoolAccountStatus());
        responseAccountInfoDto.setProfiles(profileInfoDto);

        return responseAccountInfoDto;
    }
}
