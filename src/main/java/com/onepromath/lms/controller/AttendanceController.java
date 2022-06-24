package com.onepromath.lms.controller;

import com.onepromath.lms.dto.attendance.calendar.RequestCalendarDto;
import com.onepromath.lms.dto.attendance.calendar.ResponseCalendarDto;
import com.onepromath.lms.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/api/attendance/calendar")
    public ResponseEntity<ArrayList<ArrayList<ResponseCalendarDto>>> calendar(@RequestBody RequestCalendarDto requestCalendarDto) throws ParseException {
        ArrayList<ArrayList<ResponseCalendarDto>> responseCalendarDtoArrayList = attendanceService.calendar(requestCalendarDto.getStudentNo(), requestCalendarDto.getStartDate());

        return ResponseEntity.ok().body(responseCalendarDtoArrayList);
    }
}
