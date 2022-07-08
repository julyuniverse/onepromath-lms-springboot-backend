package com.onepromath.lms.controller;

import com.onepromath.lms.dto.classes.averageclass.RequestAverageClassDto;
import com.onepromath.lms.dto.classes.averageclass.ResponseAverageClassDto;
import com.onepromath.lms.dto.classes.classes.RequestClassDto;
import com.onepromath.lms.dto.classes.classes.ResponseClassDto;
import com.onepromath.lms.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lms-backend")
public class ClassController { // 학급
    private final ClassService classService;

    // 반 목록
    @PostMapping("/class/class")
    public ResponseEntity<ArrayList<ResponseClassDto>> classes(@RequestBody RequestClassDto requestClassDto) {
        ArrayList<ResponseClassDto> classDtoArrayList = classService.classes(requestClassDto.getSchoolInfoNo(), requestClassDto.getSchoolYear());

        return ResponseEntity.ok().body(classDtoArrayList);
    }

    // 평균 반
    @PostMapping("/class/average-class")
    public ResponseEntity<ResponseAverageClassDto> averageClass(@RequestBody RequestAverageClassDto requestAverageClassDto) {
        ResponseAverageClassDto responseAverageClassDto = classService.averageClass(requestAverageClassDto.getSchoolInfoNo(), requestAverageClassDto.getSchoolClassNo(), requestAverageClassDto.getStartDate(), requestAverageClassDto.getEndDate());

        return ResponseEntity.ok().body(responseAverageClassDto);
    }
}
