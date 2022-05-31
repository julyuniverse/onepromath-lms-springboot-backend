package com.onepromath.lms.service.student;

import com.onepromath.lms.dto.student.weekly.ResponseWeeklyStudentDto;
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
    public ArrayList<ResponseWeeklyStudentDto> weeklyStudents(String startDate, String endDate, int schoolInfoNo, int schoolClassNo) {
        ArrayList<ResponseWeeklyStudentDto> responseWeeklyStudentDtoArrayList = studentMapper.weeklyStudents(startDate, endDate, schoolInfoNo, schoolClassNo);

        return responseWeeklyStudentDtoArrayList;
    }
}
