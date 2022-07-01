package com.onepromath.lms.controller;

import com.onepromath.lms.dto.student.average.RequestAverageStudentDto;
import com.onepromath.lms.dto.student.average.ResponseAverageStudentDto;
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
    @PostMapping("/api/student/weekly-students")
    public ResponseEntity<ArrayList<ResponseWeeklyStudentDto>> weeklyStudents(@RequestBody RequestWeeklyStudentDto requestWeeklyStudentDto) {
        ArrayList<ResponseWeeklyStudentDto> responseWeeklyStudentDtoArrayList = studentService.weeklyStudents(requestWeeklyStudentDto.getSchoolInfoNo(), requestWeeklyStudentDto.getSchoolClassNo(), requestWeeklyStudentDto.getStartDate(), requestWeeklyStudentDto.getEndDate(), requestWeeklyStudentDto.getSort(), requestWeeklyStudentDto.isOrder());

        return ResponseEntity.ok().body(responseWeeklyStudentDtoArrayList);
    }

    // 평균 모든 학생
    @PostMapping("/api/student/average-students")
    public ResponseEntity<ArrayList<ResponseAverageStudentDto>> averageStudents(@RequestBody RequestAverageStudentDto requestAverageStudentDto) {
        ArrayList<ResponseAverageStudentDto> responseAverageStudentDtoArrayList = studentService.averageStudents(requestAverageStudentDto.getSchoolInfoNo(), requestAverageStudentDto.getSchoolClassNo(), requestAverageStudentDto.getStartDate(), requestAverageStudentDto.getEndDate(), requestAverageStudentDto.getSort(), requestAverageStudentDto.isOrder());

        return ResponseEntity.ok().body(responseAverageStudentDtoArrayList);
    }
}
