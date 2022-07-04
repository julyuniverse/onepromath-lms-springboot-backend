package com.onepromath.lms.controller;

import com.onepromath.lms.dto.account.info.RequestAccountInfoDto;
import com.onepromath.lms.dto.account.info.ResponseAccountInfoDto;
import com.onepromath.lms.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lee Taesung
 * @since 7/4/22
 */

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    // 계정 정보
    @PostMapping("/api/account/info")
    public ResponseEntity<ResponseAccountInfoDto> accountInfo(@RequestBody RequestAccountInfoDto requestAccountInfoDto) {
        ResponseAccountInfoDto responseAccountInfoDto = accountService.accountInfo(requestAccountInfoDto.getUserNo());

        return ResponseEntity.ok().body(responseAccountInfoDto);
    }

    // 계정 정보2
    @PostMapping("/api/account/info2")
    public ResponseEntity<ResponseAccountInfoDto> accountInfo2(@RequestBody RequestAccountInfoDto requestAccountInfoDto) {
        ResponseAccountInfoDto responseAccountInfoDto = accountService.accountInfo2(requestAccountInfoDto.getProfileNo());

        return ResponseEntity.ok().body(responseAccountInfoDto);
    }
}
