package com.onepromath.lms.service.classes;

import com.onepromath.lms.dto.classes.ResponseClassDto;
import com.onepromath.lms.mapper.ClassMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassService {
    private final ClassMapper classMapper;

    public ClassService(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    public ArrayList<ResponseClassDto> classes(int schoolInfoNo, int schoolYear) {
        ArrayList<ResponseClassDto> classDtoArrayList = classMapper.classes(schoolInfoNo, schoolYear);

        return classDtoArrayList;
    }
}
