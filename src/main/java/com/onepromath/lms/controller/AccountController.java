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
@RequestMapping("/api/lms-backend") // 운영 단계에서는 apache에서 요청 주소를 www.example.com/api/lms-backend로 설정하기 때문에 해당 @RequestMapping은 주석 처리하거나 삭제하도록 한다.
public class AccountController {
    private final AccountService accountService;

    // 계정 정보
    @PostMapping("/account/info")
    public ResponseEntity<ResponseAccountInfoDto> accountInfo(@RequestBody RequestAccountInfoDto requestAccountInfoDto) {
        ResponseAccountInfoDto responseAccountInfoDto = accountService.accountInfo(requestAccountInfoDto.getUserNo());

        return ResponseEntity.ok().body(responseAccountInfoDto);
    }

    // 계정 정보2
    @PostMapping("/account/info2")
    public ResponseEntity<ResponseAccountInfoDto> accountInfo2(@RequestBody RequestAccountInfoDto requestAccountInfoDto) {
        ResponseAccountInfoDto responseAccountInfoDto = accountService.accountInfo2(requestAccountInfoDto.getProfileNo());

        return ResponseEntity.ok().body(responseAccountInfoDto);
    }
}
