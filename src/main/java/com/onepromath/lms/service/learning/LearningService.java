package com.onepromath.lms.service.learning;

import com.onepromath.lms.dto.learning.data.ResponseLearningDataDto;
import com.onepromath.lms.dto.learning.data.day.DayLearningDataDto;
import com.onepromath.lms.dto.learning.data.level.ResponseLevelAndChapterDataDto;
import com.onepromath.lms.dto.learning.data.level.ResponseLevelDataDto;
import com.onepromath.lms.dto.learning.data.month.ResponseMonthlyLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.ResponseLastWeekAndThisWeekLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.ResponseWeeklyLearningDataDto;
import com.onepromath.lms.mapper.LearningMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Lee Taesung
 * @since 6/23/22
 */

@Service
@Transactional
public class LearningService {
    private final LearningMapper learningMapper;

    public LearningService(LearningMapper learningMapper) {
        this.learningMapper = learningMapper;
    }

    // 월별 학습 데이터
    public ArrayList<ResponseMonthlyLearningDataDto> monthlyLearningData(int studentNo, String startDate, int count) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("MM");

        Date date = format.parse(startDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -(count - 1));
        String startDate2 = format.format(calendar.getTime()); // 시작일

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.add(Calendar.MONTH, +1);
        String endDate = format.format(calendar2.getTime()); // 끝일

        // 몇 달 ArrayList 만들기.
        ArrayList<ResponseMonthlyLearningDataDto> responseMonthlyLearningDataDtoArrayList = new ArrayList<>();
        ArrayList<ResponseMonthlyLearningDataDto> responseMonthlyLearningDataDtoArrayList1 = learningMapper.monthlyLearningData(studentNo, startDate2, endDate);

        boolean insertFlag;
        for (int i = 0; i < count; i++) {
            insertFlag = false;
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date);
            calendar3.add(Calendar.MONTH, -(count - (i + 1)));
            String thisDate = format.format(calendar3.getTime());
            String thisYear = format1.format(calendar3.getTime());
            String thisMonth = format2.format(calendar3.getTime());

            for (ResponseMonthlyLearningDataDto responseMonthlyLearningDataDto : responseMonthlyLearningDataDtoArrayList1) {
                if (thisDate.equals(responseMonthlyLearningDataDto.getDate())) {
                    responseMonthlyLearningDataDtoArrayList.add(responseMonthlyLearningDataDto);
                    insertFlag = true;
                }
            }

            if (!insertFlag) {
                ResponseMonthlyLearningDataDto responseMonthlyLearningDataDto = new ResponseMonthlyLearningDataDto();
                responseMonthlyLearningDataDto.setDate(thisDate);
                responseMonthlyLearningDataDto.setYear(thisYear);
                responseMonthlyLearningDataDto.setMonth(thisMonth);

                responseMonthlyLearningDataDtoArrayList.add(responseMonthlyLearningDataDto);
            }
        }

        return responseMonthlyLearningDataDtoArrayList;
    }

    // 주별 학습 데이터
    public ArrayList<ResponseWeeklyLearningDataDto> weeklyLearningData(int studentNo, String startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse(startDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 요일
        int numberOfDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 일수
        int numberOfWeek = (int) Math.ceil((float) (numberOfDay + (dayOfWeek == 1 ? 7 : dayOfWeek - 1) - 1) / 7); // 주차 수 (요일을 월요일 기준으로 설정했기 때문에 총 일수에서 1을 빼준다.)

        String startDate2 = null;
        if (dayOfWeek > 1) { // 해당 주 월요일 기준으로 설정
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date);
            calendar3.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startDate2 = format.format(calendar3.getTime());
        } else { // 지난 주 월요일 기준으로 설정
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
            calendar2.add(Calendar.DATE, -7);
            String tmpDate = format.format(calendar2.getTime());

            Date date2 = format.parse(tmpDate);

            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date2);
            calendar3.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startDate2 = format.format(calendar3.getTime());
        }

        Date date2 = format.parse(startDate2);

        ArrayList<ResponseWeeklyLearningDataDto> responseWeeklyLearningDataDtoArrayList = new ArrayList<>();
        String tmpStartDate = startDate2;
        for (int i = 1; i <= numberOfWeek; i++) { // 시작일로부터 주차 수만큼 7일씩 더하기.
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            calendar2.add(Calendar.DATE, (i * 7));
            String tmpDate = format.format(calendar2.getTime());

            // 주별 학습 데이터 ArrayList에 추가
            ResponseWeeklyLearningDataDto responseWeeklyLearningDataDto = learningMapper.weeklyLearningData(studentNo, tmpStartDate, tmpDate);
            responseWeeklyLearningDataDto.setDate(tmpStartDate);
            responseWeeklyLearningDataDtoArrayList.add(responseWeeklyLearningDataDto);

            tmpStartDate = tmpDate;
        }

        return responseWeeklyLearningDataDtoArrayList;
    }

    // 레벨 데이터
    public ArrayList<ResponseLevelDataDto> levelData(int studentNo, String startDate, String endDate) {
        String[] color = {
                "#ffea00", // 1레벨
                "#ffbe42", // 2레벨
                "#ffa500", // 3레벨
                "#ff5349", // 4레벨
                "#ff0000", // 5레벨
                "#c71585", // 6레벨
                "#ee82ee", // 7레벨
                "#8a2be2", // 8레벨
                "#0000ff", // 9레벨
                "#0d98ba", // 10레벨
                "#00ff00", // 11레벨
                "#9acd32" // 12레벨
        };

        ArrayList<ResponseLevelDataDto> responseLevelDataDtoArrayList = learningMapper.levelData(studentNo, startDate, endDate);

        int learningCount = 0; // 학습량
        for (ResponseLevelDataDto responseLevelDataDto : responseLevelDataDtoArrayList) {
            learningCount += responseLevelDataDto.getValue();
        }

        for (ResponseLevelDataDto responseLevelDataDto : responseLevelDataDtoArrayList) {
            for (int i = 0; i < color.length; i++) {
                if (responseLevelDataDto.getId() == (i + 1)) {
                    responseLevelDataDto.setPercent((float) (Math.round((float) responseLevelDataDto.getValue() / learningCount * 100 * 10) / 10.0));
                    responseLevelDataDto.setColor(color[i]);
                }
            }
        }

        boolean isEmpty = responseLevelDataDtoArrayList.isEmpty(); // ArrayList가 비었는지 확인
        if (isEmpty) { // 비어있다면 임시 데이터 추가
            responseLevelDataDtoArrayList.add(new ResponseLevelDataDto(0, 1, 100, "#a576fa"));
        }

        return responseLevelDataDtoArrayList;
    }

    // 레벨과 챕터 데이터
    public ArrayList<ResponseLevelAndChapterDataDto> levelAndChapterData(int studentNo, String startDate, String endDate) {
        // 레벨 챕터 색상 코드
        String[][] color = {
                {"#fff79d", "#fff589", "#fff476", "#fff262", "#fff04e", "#ffef3b", "#ffed27", "#ffec14", "#ffea00"},
                {"#fff4df", "#ffedcb", "#ffe6b8", "#ffe0a4", "#ffd990", "#ffd27d", "#ffcb69", "#ffc556", "#ffbe42"},
                {"#ffd589", "#ffcf76", "#ffc862", "#ffc14e", "#ffba3b", "#ffb327", "#ffac14", "#ffa500"},
                {"#ffe7e6", "#ffd5d2", "#ffc2bf", "#ffb0ab", "#ff9d97", "#ff8b84", "#ff7870", "#ff665d", "#ff5349"},
                {"#ffb1b1", "#ff9d9d", "#ff8989", "#ff7676", "#ff6262", "#ff4e4e", "#ff3b3b", "#ff2727", "#ff1414", "#ff0000"},
                {"#f287ca", "#f075c3", "#ef63bb", "#ed51b3", "#eb40ab", "#e92ea4", "#e71c9c", "#d91791", "#c71585"},
                {"#fceafc", "#fad8fa", "#f7c7f7", "#f5b6f5", "#f3a5f3", "#f093f0", "#ee82ee", "#ec71ec", "#e95fe9"},
                {"#f3e9fc", "#e9d8fa", "#e0c6f7", "#d6b5f5", "#cda4f3", "#c393f0", "#ba81ee", "#b070eb", "#a75fe9", "#9d4ee7", "#943ce4", "#8a2be2", "#801edb", "#761cca", "#6c19b9"},
                {"#d8d8ff", "#c4c4ff", "#b1b1ff", "#9d9dff", "#8989ff", "#7676ff", "#6262ff", "#4e4eff", "#3b3bff", "#2727ff", "#1414ff", "#0000ff"},
                {"#5dd6f4", "#4ad1f2", "#38cdf1", "#26c8f0", "#13c3ef", "#10b6df", "#0ea7cc", "#0d98ba"},
                {"#76ff76", "#62ff62", "#4eff4e", "#3bff3b", "#27ff27", "#14ff14", "#00ff00"},
                {"#d0e8a0", "#c9e491", "#c1e081", "#b9dc71", "#b1d961", "#aad552", "#a2d142", "#9acd32"}
        };

        ArrayList<ResponseLevelAndChapterDataDto> responseLevelAndChapterDataDtoArrayList = learningMapper.levelAndChapterData(studentNo, startDate, endDate);

        int learningCount = 0; // 학습량
        for (ResponseLevelAndChapterDataDto responseLevelAndChapterDataDto : responseLevelAndChapterDataDtoArrayList) {
            learningCount += responseLevelAndChapterDataDto.getValue();
        }

        for (ResponseLevelAndChapterDataDto responseLevelAndChapterDataDto : responseLevelAndChapterDataDtoArrayList) {
            responseLevelAndChapterDataDto.setPercent((float) (Math.round((float) responseLevelAndChapterDataDto.getValue() / learningCount * 100 * 10) / 10.0));
            responseLevelAndChapterDataDto.setColor(color[responseLevelAndChapterDataDto.getLevel() - 1][responseLevelAndChapterDataDto.getChapter() - 1]);
        }

        boolean isEmpty = responseLevelAndChapterDataDtoArrayList.isEmpty(); // ArrayList가 비었는지 확인
        if (isEmpty) { // 비어있다면 임시 데이터 추가
            responseLevelAndChapterDataDtoArrayList.add(new ResponseLevelAndChapterDataDto(0, 0, 0, "", 1, 100, "#a576fa"));
        }

        return responseLevelAndChapterDataDtoArrayList;
    }

    // 학습 데이터
    public ArrayList<ResponseLearningDataDto> learningData(int studentNo, String startDate, String endDate) {
        ArrayList<ResponseLearningDataDto> responseLearningDataDtoArrayList = learningMapper.learningData(studentNo, startDate, endDate);

        return responseLearningDataDtoArrayList;
    }

    // 지난주와 이번 주 학습 데이터
    public ArrayList<ResponseLastWeekAndThisWeekLearningDataDto> lastWeekAndThisWeekLearningData(int studentNo, String startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse(startDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 14);
        String endDate = format.format(calendar.getTime());
        System.out.println(endDate);

        ArrayList<DayLearningDataDto> dayLearningDataDtoArrayList = learningMapper.dailyLearningData(studentNo, startDate, endDate);
        ArrayList<ResponseLastWeekAndThisWeekLearningDataDto> learningData = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            ResponseLastWeekAndThisWeekLearningDataDto responseLastWeekAndThisWeekLearningDataDto = new ResponseLastWeekAndThisWeekLearningDataDto();
            learningData.add(responseLastWeekAndThisWeekLearningDataDto);
        }

        boolean insertFlag;
        for (int i = 0; i < 14; i++) {
            insertFlag = true;
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
            calendar2.add(Calendar.DATE, i);
            String tmpDate = format.format(calendar2.getTime());

            for (DayLearningDataDto dayLearningDataDto : dayLearningDataDtoArrayList) {
                if (i < 7) { // 지난주
                    if (Objects.equals(dayLearningDataDto.getLearningDate(), tmpDate)) {
                        learningData.get(i).setLastWeekLearningDate(dayLearningDataDto.getLearningDate());
                        learningData.get(i).setLastWeekDailyLearningCount(dayLearningDataDto.getDailyLearningCount());
                        learningData.get(i).setLastWeekFreeLearningCount(dayLearningDataDto.getFreeLearningCount());
                        learningData.get(i).setLastWeekOneproLearningCount(dayLearningDataDto.getOneproLearningCount());
                        learningData.get(i).setLastWeekWorldLearningCount(dayLearningDataDto.getWorldLearningCount());
                        learningData.get(i).setLastWeekLearningCount(dayLearningDataDto.getLearningCount());
                        learningData.get(i).setLastWeekDailyAccuracy(dayLearningDataDto.getDailyAccuracy());
                        learningData.get(i).setLastWeekFreeAccuracy(dayLearningDataDto.getFreeAccuracy());
                        learningData.get(i).setLastWeekOneproAccuracy(dayLearningDataDto.getOneproAccuracy());
                        learningData.get(i).setLastWeekWorldAccuracy(dayLearningDataDto.getWorldAccuracy());
                        learningData.get(i).setLastWeekAccuracy(dayLearningDataDto.getAccuracy());
                        learningData.get(i).setLastWeekDailyLearningTimeMinutes(dayLearningDataDto.getDailyLearningTimeMinutes());
                        learningData.get(i).setLastWeekFreeLearningTimeMinutes(dayLearningDataDto.getFreeLearningTimeMinutes());
                        learningData.get(i).setLastWeekOneproLearningTimeMinutes(dayLearningDataDto.getOneproLearningTimeMinutes());
                        learningData.get(i).setLastWeekWorldLearningTimeMinutes(dayLearningDataDto.getWorldLearningTimeMinutes());
                        learningData.get(i).setLastWeekLearningTimeMinutes(dayLearningDataDto.getLearningTimeMinutes());
                        insertFlag = false;
                        break;
                    }
                } else { // 이번 주
                    if (Objects.equals(dayLearningDataDto.getLearningDate(), tmpDate)) {
                        learningData.get(i - 7).setThisWeekLearningDate(dayLearningDataDto.getLearningDate());
                        learningData.get(i - 7).setThisWeekDailyLearningCount(dayLearningDataDto.getDailyLearningCount());
                        learningData.get(i - 7).setThisWeekFreeLearningCount(dayLearningDataDto.getFreeLearningCount());
                        learningData.get(i - 7).setThisWeekOneproLearningCount(dayLearningDataDto.getOneproLearningCount());
                        learningData.get(i - 7).setThisWeekWorldLearningCount(dayLearningDataDto.getWorldLearningCount());
                        learningData.get(i - 7).setThisWeekLearningCount(dayLearningDataDto.getLearningCount());
                        learningData.get(i - 7).setThisWeekDailyAccuracy(dayLearningDataDto.getDailyAccuracy());
                        learningData.get(i - 7).setThisWeekFreeAccuracy(dayLearningDataDto.getFreeAccuracy());
                        learningData.get(i - 7).setThisWeekOneproAccuracy(dayLearningDataDto.getOneproAccuracy());
                        learningData.get(i - 7).setThisWeekWorldAccuracy(dayLearningDataDto.getWorldAccuracy());
                        learningData.get(i - 7).setThisWeekAccuracy(dayLearningDataDto.getAccuracy());
                        learningData.get(i - 7).setThisWeekDailyLearningTimeMinutes(dayLearningDataDto.getDailyLearningTimeMinutes());
                        learningData.get(i - 7).setThisWeekFreeLearningTimeMinutes(dayLearningDataDto.getFreeLearningTimeMinutes());
                        learningData.get(i - 7).setThisWeekOneproLearningTimeMinutes(dayLearningDataDto.getOneproLearningTimeMinutes());
                        learningData.get(i - 7).setThisWeekWorldLearningTimeMinutes(dayLearningDataDto.getWorldLearningTimeMinutes());
                        learningData.get(i - 7).setThisWeekLearningTimeMinutes(dayLearningDataDto.getLearningTimeMinutes());
                        insertFlag = false;
                        break;
                    }
                }
            }

            if (insertFlag) {
                if (i < 7) { // 지난주
                    learningData.get(i).setLastWeekLearningDate(tmpDate);
                } else { // 이번 주
                    learningData.get(i - 7).setThisWeekLearningDate(tmpDate);
                }
            }
        }

        return learningData;
    }

    // 일별 학습 데이터
    public ArrayList<DayLearningDataDto> dailyLearningData(int studentNo, String startDate, String endDate) {
        ArrayList<DayLearningDataDto> dailyLearningData = learningMapper.dailyLearningData(studentNo, startDate, endDate);

        return dailyLearningData;
    }
}
