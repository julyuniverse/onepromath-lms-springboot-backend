package com.onepromath.lms.controller;

import com.onepromath.lms.dto.classes.RequestClassDto;
import com.onepromath.lms.dto.classes.ResponseClassDto;
import com.onepromath.lms.service.classes.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class ClassController { // 학급
    private final ClassService classService;

    @PostMapping("/api/class")
    public ResponseEntity<ArrayList<ResponseClassDto>> classes(@RequestBody RequestClassDto requestClassDto) {
        ArrayList<ResponseClassDto> classDtoArrayList = classService.classes(requestClassDto.getSchoolInfoNo(), requestClassDto.getSchoolYear());

        return ResponseEntity.ok().body(classDtoArrayList);
    }
}
