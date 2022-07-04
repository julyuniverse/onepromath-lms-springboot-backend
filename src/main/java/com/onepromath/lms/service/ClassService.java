package com.onepromath.lms.service;

import com.onepromath.lms.dto.classes.averageclass.ResponseAverageClassDto;
import com.onepromath.lms.dto.classes.classes.ResponseClassDto;
import com.onepromath.lms.mapper.ClassMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class ClassService {
    private final ClassMapper classMapper;

    public ClassService(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    // 반 목록
    public ArrayList<ResponseClassDto> classes(int schoolInfoNo, int schoolYear) {
        ArrayList<ResponseClassDto> classDtoArrayList;
        if (schoolYear == 999) {
            classDtoArrayList = classMapper.classes2(schoolInfoNo);
        } else if (schoolYear == 7) {
            classDtoArrayList = classMapper.classes3(schoolInfoNo);
        } else {
            classDtoArrayList = classMapper.classes(schoolInfoNo, schoolYear);
        }
        return classDtoArrayList;
    }

    // 평균 반
    public ResponseAverageClassDto averageClass(int schoolInfoNo, int schoolClassNo, String startDate, String endDate) {
        ResponseAverageClassDto responseAverageClassDto = classMapper.averageClass(schoolInfoNo, schoolClassNo, startDate, endDate);

        return responseAverageClassDto;
    }
}
