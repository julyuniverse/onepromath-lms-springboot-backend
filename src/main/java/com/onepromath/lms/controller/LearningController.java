package com.onepromath.lms.controller;

import com.onepromath.lms.dto.learning.data.RequestLearningDataDto;
import com.onepromath.lms.dto.learning.data.ResponseLearningDataDto;
import com.onepromath.lms.dto.learning.data.day.DayLearningDataDto;
import com.onepromath.lms.dto.learning.data.day.RequestDailyLearningDataDto;
import com.onepromath.lms.dto.learning.data.level.RequestLevelAndChapterDataDto;
import com.onepromath.lms.dto.learning.data.level.RequestLevelDataDto;
import com.onepromath.lms.dto.learning.data.level.ResponseLevelAndChapterDataDto;
import com.onepromath.lms.dto.learning.data.level.ResponseLevelDataDto;
import com.onepromath.lms.dto.learning.data.month.RequestMonthlyLearningDataDto;
import com.onepromath.lms.dto.learning.data.month.ResponseMonthlyLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.RequestLastWeekAndThisWeekLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.RequestWeeklyLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.ResponseLastWeekAndThisWeekLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.ResponseWeeklyLearningDataDto;
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
public class LearningController { // 학습
    private final LearningService learningService;

    // 월별 학습 데이터
    @PostMapping("/api/learning/monthly-learning-data")
    public ResponseEntity<ArrayList<ResponseMonthlyLearningDataDto>> monthlyLearningData(@RequestBody RequestMonthlyLearningDataDto requestMonthlyLearningDataDto) throws ParseException {
        ArrayList<ResponseMonthlyLearningDataDto> responseMonthlyLearningDataDtoArrayList = learningService.monthlyLearningData(requestMonthlyLearningDataDto.getStudentNo(), requestMonthlyLearningDataDto.getStartDate(), requestMonthlyLearningDataDto.getCount());

        return ResponseEntity.ok().body(responseMonthlyLearningDataDtoArrayList);
    }

    // 주별 학습 데이터
    @PostMapping("/api/learning/weekly-learning-data")
    public ResponseEntity<ArrayList<ResponseWeeklyLearningDataDto>> weeklyLearningData(@RequestBody RequestWeeklyLearningDataDto requestWeeklyLearningDataDto) throws ParseException {
        ArrayList<ResponseWeeklyLearningDataDto> responseWeeklyLearningDataDtoArrayList = learningService.weeklyLearningData(requestWeeklyLearningDataDto.getStudentNo(), requestWeeklyLearningDataDto.getStartDate());

        return ResponseEntity.ok().body(responseWeeklyLearningDataDtoArrayList);
    }

    // 레벨 데이터
    @PostMapping("/api/learning/level-data")
    public ResponseEntity<ArrayList<ResponseLevelDataDto>> levelData(@RequestBody RequestLevelDataDto requestLevelDataDto) {
        ArrayList<ResponseLevelDataDto> responseLevelDataDtoArrayList = learningService.levelData(requestLevelDataDto.getStudentNo(), requestLevelDataDto.getStartDate(), requestLevelDataDto.getEndDate());

        return ResponseEntity.ok().body(responseLevelDataDtoArrayList);
    }

    // 레벨과 챕터 데이터
    @PostMapping("/api/learning/level-and-chapter-data")
    public ResponseEntity<ArrayList<ResponseLevelAndChapterDataDto>> levelAndChapterData(@RequestBody RequestLevelAndChapterDataDto requestLevelAndChapterDataDto) {
        ArrayList<ResponseLevelAndChapterDataDto> responseLevelAndChapterDataDtoArrayList = learningService.levelAndChapterData(requestLevelAndChapterDataDto.getStudentNo(), requestLevelAndChapterDataDto.getStartDate(), requestLevelAndChapterDataDto.getEndDate());

        return ResponseEntity.ok().body(responseLevelAndChapterDataDtoArrayList);
    }

    // 학습 데이터
    @PostMapping("/api/learning/learning-data")
    public ResponseEntity<ArrayList<ResponseLearningDataDto>> learningData(@RequestBody RequestLearningDataDto requestLearningDataDto) {
        ArrayList<ResponseLearningDataDto> responseLearningDataDtoArrayList = learningService.learningData(requestLearningDataDto.getStudentNo(), requestLearningDataDto.getStartDate(), requestLearningDataDto.getEndDate());

        return ResponseEntity.ok().body(responseLearningDataDtoArrayList);
    }

    // 지난 주와 이번 주 학습 데이터
    @PostMapping("/api/learning/last-week-and-this-week-learning-data")
    public ResponseEntity<ArrayList<ResponseLastWeekAndThisWeekLearningDataDto>> lastWeekAndThisWeekLearningData(@RequestBody RequestLastWeekAndThisWeekLearningDataDto requestLastWeekAndThisWeekLearningDataDto) throws ParseException {
        ArrayList<ResponseLastWeekAndThisWeekLearningDataDto> learningData = learningService.lastWeekAndThisWeekLearningData(requestLastWeekAndThisWeekLearningDataDto.getStudentNo(), requestLastWeekAndThisWeekLearningDataDto.getStartDate());

        return ResponseEntity.ok().body(learningData);
    }

    // 일별 학습 데이터
    @PostMapping("/api/learning/daily-learning-data")
    public ResponseEntity<ArrayList<DayLearningDataDto>> dailyLearningData(@RequestBody RequestDailyLearningDataDto requestDailyLearningDataDto) {
        ArrayList<DayLearningDataDto> dailyLearningData = learningService.dailyLearningData(requestDailyLearningDataDto.getStudentNo(), requestDailyLearningDataDto.getStartDate(), requestDailyLearningDataDto.getEndDate());

        return ResponseEntity.ok().body(dailyLearningData);
    }
}
