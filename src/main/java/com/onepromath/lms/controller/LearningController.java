package com.onepromath.lms.controller;

import com.onepromath.lms.dto.learning.data.month.RequestMonthlyLearningDataDto;
import com.onepromath.lms.dto.learning.data.month.ResponseMonthlyLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.RequestWeeklyLearningDataDto;
import com.onepromath.lms.service.learning.LearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author Lee Taesung
 * @since 6/23/22
 */

@RestController
@RequiredArgsConstructor
public class LearningController {
    private final LearningService learningService;

    // 월별 학습 데이터
    @PostMapping("/api/learning/monthly-learning-data")
    public ResponseEntity<ArrayList<ResponseMonthlyLearningDataDto>> monthlyLearningData(@RequestBody RequestMonthlyLearningDataDto requestMonthlyLearningDataDto) throws ParseException {
        ArrayList<ResponseMonthlyLearningDataDto> responseMonthlyLearningDataDtoArrayList = learningService.monthlyLearningData(requestMonthlyLearningDataDto.getStudentNo(), requestMonthlyLearningDataDto.getStartDate(), requestMonthlyLearningDataDto.getCount());

        return ResponseEntity.ok().body(responseMonthlyLearningDataDtoArrayList);
    }

    // 주별 학습 데이터
    @PostMapping("/api/learning/weekly-learning-data")
    public ResponseEntity<String> weeklyLearningData(@RequestBody RequestWeeklyLearningDataDto requestWeeklyLearningDataDto) throws ParseException {
        String str = learningService.weeklyLearningData(requestWeeklyLearningDataDto.getStudentNo(), requestWeeklyLearningDataDto.getStartDate());

        return ResponseEntity.ok().body(str);
    }
}
