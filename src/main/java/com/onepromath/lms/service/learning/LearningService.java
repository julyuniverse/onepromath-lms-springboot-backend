package com.onepromath.lms.service.learning;

import com.onepromath.lms.dto.learning.data.month.ResponseMonthlyLearningDataDto;
import com.onepromath.lms.mapper.LearningMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        for(int i = 0; i < count; i++) {
            insertFlag = false;
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date);
            calendar3.add(Calendar.MONTH, -(count - (i + 1)));
            String thisDate = format.format(calendar3.getTime());
            String thisYear = format1.format(calendar3.getTime());
            String thisMonth = format2.format(calendar3.getTime());

            for (ResponseMonthlyLearningDataDto responseMonthlyLearningDataDto : responseMonthlyLearningDataDtoArrayList1){
                if(thisDate.equals(responseMonthlyLearningDataDto.getDate())) {
                    responseMonthlyLearningDataDtoArrayList.add(responseMonthlyLearningDataDto);
                    insertFlag = true;
                }
            }

            if(!insertFlag) {
                ResponseMonthlyLearningDataDto responseMonthlyLearningDataDto = new ResponseMonthlyLearningDataDto();
                responseMonthlyLearningDataDto.setDate(thisDate);
                responseMonthlyLearningDataDto.setYear(thisYear);
                responseMonthlyLearningDataDto.setMonth(thisMonth);

                responseMonthlyLearningDataDtoArrayList.add(responseMonthlyLearningDataDto);
            }
        }

        System.out.println(responseMonthlyLearningDataDtoArrayList);

//        return "시작일: " + startDate2 + ", 끝일: " + endDate;
        return responseMonthlyLearningDataDtoArrayList;
    }

    // 주별 학습 데이터
    public String weeklyLearningData(int studentNo, String startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse(startDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 요일
        int numberOfDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 일수
        int numberOfWeek = (int) Math.ceil((float) (numberOfDay + (dayOfWeek == 1 ? 7 : dayOfWeek - 1)) / 7); // 주차 수

        String startDate2 = null;
        if(dayOfWeek > 1) { // 해당 주 월요일 기준으로 설정
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date);
            calendar3.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startDate2 = format.format(calendar3.getTime());
        }else { // 지난 주 월요일 기준으로 설정
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

        System.out.println(startDate2);

        for (int i = 0; i < numberOfWeek; i++) {

        }


        return "123";
    }
}
