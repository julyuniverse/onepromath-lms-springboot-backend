package com.onepromath.lms.service.student;

import com.onepromath.lms.dto.student.ResponseStudentDto;
import com.onepromath.lms.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
    // 모든 학생
    public ArrayList<ResponseStudentDto> students(int schoolInfoNo, int schoolClassNo) {
        ArrayList<ResponseStudentDto> studentDtoArrayList = studentMapper.students(schoolInfoNo, schoolClassNo);

        return studentDtoArrayList;
    }
}
