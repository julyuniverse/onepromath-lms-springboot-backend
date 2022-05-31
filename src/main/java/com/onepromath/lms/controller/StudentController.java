package com.onepromath.lms.controller;

import com.onepromath.lms.dto.student.weekly.RequestWeeklyStudentDto;
import com.onepromath.lms.dto.student.weekly.ResponseWeeklyStudentDto;
import com.onepromath.lms.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class StudentController { // 학생
    private final StudentService studentService;

    // 주간 모든 학생
    @PostMapping("/api/student/weeklystudents")
    public ResponseEntity<ArrayList<ResponseWeeklyStudentDto>> weeklyStudents(@RequestBody RequestWeeklyStudentDto requestWeeklyStudentDto) {
        ArrayList<ResponseWeeklyStudentDto> responseWeeklyStudentDtoArrayList = studentService.weeklyStudents(requestWeeklyStudentDto.getStartDate(), requestWeeklyStudentDto.getEndDate(), requestWeeklyStudentDto.getSchoolInfoNo(), requestWeeklyStudentDto.getSchoolClassNo());

        return ResponseEntity.ok().body(responseWeeklyStudentDtoArrayList);
    }
}
