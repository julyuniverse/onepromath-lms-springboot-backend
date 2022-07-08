package com.onepromath.lms.controller;

import com.onepromath.lms.dto.attendance.RequestAttendanceCalendarDto;
import com.onepromath.lms.dto.attendance.RequestAttendanceWeekDto;
import com.onepromath.lms.dto.attendance.ResponseAttendanceCalendarDto;
import com.onepromath.lms.dto.attendance.ResponseAttendanceWeekDto;
import com.onepromath.lms.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lms-backend")
public class AttendanceController { // 출석
    private final AttendanceService attendanceService;

    // 달력 (출석, 학습 데이터)
    @PostMapping("/attendance/calendar")
    public ResponseEntity<ArrayList<ArrayList<ResponseAttendanceCalendarDto>>> calendar(@RequestBody RequestAttendanceCalendarDto requestAttendanceCalendarDto) throws ParseException {
        ArrayList<ArrayList<ResponseAttendanceCalendarDto>> responseCalendarDtoArrayList = attendanceService.calendar(requestAttendanceCalendarDto.getStudentNo(), requestAttendanceCalendarDto.getStartDate());

        return ResponseEntity.ok().body(responseCalendarDtoArrayList);
    }

    // 주간 (출석, 학습 데이터)
    @PostMapping("/attendance/week")
    public ResponseEntity<ArrayList<ResponseAttendanceWeekDto>> week(@RequestBody RequestAttendanceWeekDto requestAttendanceWeekDto) throws ParseException {
        ArrayList<ResponseAttendanceWeekDto> responseAttendanceWeekDtoArrayList = attendanceService.week(requestAttendanceWeekDto.getStudentNo(), requestAttendanceWeekDto.getStartDate());

        return ResponseEntity.ok().body(responseAttendanceWeekDtoArrayList);
    }
}
