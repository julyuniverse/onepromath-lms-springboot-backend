package com.onepromath.lms.controller;

import com.onepromath.lms.dto.student.RequestStudentDto;
import com.onepromath.lms.dto.student.ResponseStudentDto;
import com.onepromath.lms.dto.student.average.RequestAverageStudentDto;
import com.onepromath.lms.dto.student.average.ResponseAverageStudentDto;
import com.onepromath.lms.dto.student.weekly.RequestWeeklyStudentDto;
import com.onepromath.lms.dto.student.weekly.ResponseWeeklyStudentDto;
import com.onepromath.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lms-backend") // 운영 단계에서는 apache에서 요청 주소를 www.example.com/api/lms-backend로 설정하기 때문에 해당 @RequestMapping은 주석 처리하거나 삭제하도록 한다.
public class StudentController { // 학생
    private final StudentService studentService;

    // 주간 모든 학생
    @PostMapping("/student/weekly-students")
    public ResponseEntity<ArrayList<ResponseWeeklyStudentDto>> weeklyStudents(@RequestBody RequestWeeklyStudentDto requestWeeklyStudentDto) {
        ArrayList<ResponseWeeklyStudentDto> responseWeeklyStudentDtoArrayList = studentService.weeklyStudents(requestWeeklyStudentDto.getSchoolInfoNo(), requestWeeklyStudentDto.getSchoolClassNo(), requestWeeklyStudentDto.getStartDate(), requestWeeklyStudentDto.getEndDate(), requestWeeklyStudentDto.getSort(), requestWeeklyStudentDto.isOrder());

        return ResponseEntity.ok().body(responseWeeklyStudentDtoArrayList);
    }

    // 평균 모든 학생
    @PostMapping("/student/average-students")
    public ResponseEntity<ArrayList<ResponseAverageStudentDto>> averageStudents(@RequestBody RequestAverageStudentDto requestAverageStudentDto) {
        ArrayList<ResponseAverageStudentDto> responseAverageStudentDtoArrayList = studentService.averageStudents(requestAverageStudentDto.getSchoolInfoNo(), requestAverageStudentDto.getSchoolClassNo(), requestAverageStudentDto.getStartDate(), requestAverageStudentDto.getEndDate(), requestAverageStudentDto.getSort(), requestAverageStudentDto.isOrder());

        return ResponseEntity.ok().body(responseAverageStudentDtoArrayList);
    }

    // 모든 학생
    @PostMapping("/student/students")
    public ResponseEntity<ArrayList<ResponseStudentDto>> students(@RequestBody RequestStudentDto requestStudentDto) {
        ArrayList<ResponseStudentDto> students = studentService.students(requestStudentDto.getSchoolNo(), requestStudentDto.getClassNo());

        return ResponseEntity.ok().body(students);
    }
}
