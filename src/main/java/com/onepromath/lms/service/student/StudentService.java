package com.onepromath.lms.service.student;

import com.onepromath.lms.dto.student.average.RequestAverageStudentDto;
import com.onepromath.lms.dto.student.average.ResponseAverageStudentDto;
import com.onepromath.lms.dto.student.weekly.ResponseWeeklyStudentDto;
import com.onepromath.lms.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class StudentService {
    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    // 주간 모든 학생
    public ArrayList<ResponseWeeklyStudentDto> weeklyStudents(int schoolInfoNo, int schoolClassNo, String startDate, String endDate, int sort, boolean order) {
        String s;
        String o;

        if (sort == 1) { // 학생 이름
            s = "student_name";
        } else if (sort == 2) { // 학습량
            s = "learning_count";
        } else {
            s = "student_name";
        }

        if (order) {
            o = "asc";
        } else {
            o = "desc";
        }

        ArrayList<ResponseWeeklyStudentDto> responseWeeklyStudentDtoArrayList = studentMapper.weeklyStudents(schoolInfoNo, schoolClassNo, startDate, endDate, s, o);

        for (ResponseWeeklyStudentDto responseWeeklyStudentDto : responseWeeklyStudentDtoArrayList) {
            responseWeeklyStudentDto.setLevel(
                    studentMapper.weeklyStudentLevel(responseWeeklyStudentDto.getStudentNo(), startDate, endDate)
            );
        }

        return responseWeeklyStudentDtoArrayList;
    }

    // 평균 모든 학생
    public ArrayList<ResponseAverageStudentDto> averageStudents(int schoolInfoNo, int schoolClassNo, String startDate, String endDate, int sort, boolean order) {
        String s;
        String o;

        if (sort == 1) { // 학생 이름
            s = "student_name";
        } else if (sort == 2) { // 학습량
            s = "learning_count";
        } else if (sort == 3) { // 정확도
            s = "accuracy";
        } else if (sort == 4) { // 학습시간
            s = "learning_time_seconds";
        } else {
            s = "student_name";
        }

        if (order) {
            o = "asc";
        } else {
            o = "desc";
        }

        ArrayList<ResponseAverageStudentDto> requestAverageStudentDtoArrayList = studentMapper.averageStudents(schoolInfoNo, schoolClassNo, startDate, endDate, s, o);

        return requestAverageStudentDtoArrayList;
    }
}
