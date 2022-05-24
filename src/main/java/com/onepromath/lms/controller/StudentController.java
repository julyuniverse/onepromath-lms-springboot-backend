package com.onepromath.lms.controller;

import com.onepromath.lms.dto.student.RequestStudentDto;
import com.onepromath.lms.dto.student.ResponseStudentDto;
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

    @PostMapping("/api/student/students")
    public ResponseEntity<ArrayList<ResponseStudentDto>> classes(@RequestBody RequestStudentDto requestStudentDto) {
        ArrayList<ResponseStudentDto> studentDtoArrayList = studentService.students(requestStudentDto.getSchoolInfoNo(), requestStudentDto.getSchoolClassNo());

        return ResponseEntity.ok().body(studentDtoArrayList);
    }
}
