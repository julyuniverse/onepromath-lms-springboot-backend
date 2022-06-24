package com.onepromath.lms.service.attendance;

import com.onepromath.lms.dto.attendance.calendar.ResponseCalendarDto;
import com.onepromath.lms.mapper.AttendanceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceMapper attendanceMapper;
    public AttendanceService(AttendanceMapper attendanceMapper) {
        this.attendanceMapper = attendanceMapper;
    }

    // 달력 (출석, 학습량)
    public ArrayList<ArrayList<ResponseCalendarDto>> calendar(int studentNo, String startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse(startDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        String startDate2 = format.format(calendar.getTime()); // 시작일

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.set(Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));

        String lastDate = format.format(calendar2.getTime()); // 해당월 말일

        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(date);
        calendar3.add(Calendar.MONTH, +1);

        String firstDayOfNextMonth = format.format(calendar3.getTime()); // 다음달 1일

        Date date2 = format.parse(lastDate);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(date2);
        calendar4.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

        String endDate = format.format(calendar4.getTime()); // 끝일

        Date date3 = format.parse(startDate2);
        Date date4 = format.parse(endDate);

        // 두 날짜 사이의 차이 값
        long seconds = (date4.getTime() - date3.getTime()) / 1000; // 초
        long minutes = (date4.getTime() - date3.getTime()) / 60000; // 분
        long hours = (date4.getTime() - date3.getTime()) / 3600000; // 시
        long days = seconds / (24 * 60 * 60); // 일자수

        System.out.println(date3);
        System.out.println(date4);

        System.out.println("seconds: " + seconds);
        System.out.println("minutes: " + minutes);
        System.out.println("hours: " + hours);
        System.out.println("days: " + days);


        // 당월 ArrayList 만들기.
        ArrayList<ResponseCalendarDto> responseCalendarDtoArrayList = attendanceMapper.calendar(studentNo, startDate, firstDayOfNextMonth);

        ArrayList<ArrayList<ResponseCalendarDto>> responseCalendarDtoArrayList2 = new ArrayList<>();

        ArrayList<ResponseCalendarDto> arrayList = null;
        int j = 0;
        boolean insertFlag;
        SimpleDateFormat format2 = new SimpleDateFormat("dd");
        for (int i = 0; i <= days; i++) {


            if(i % 7 == 0) { // 7로 나눠서 0이 될 때마다 ArrayList 생성
                arrayList = new ArrayList<>();
                j = 0;
            }
            insertFlag = false;
            Calendar calendar5 = Calendar.getInstance();
            calendar5.setTime(date3);
            calendar5.add(Calendar.DATE, i);
            String thisDate = format.format(calendar5.getTime());
            String thisDay = format2.format(calendar5.getTime());

            for(ResponseCalendarDto responseCalendarDto: responseCalendarDtoArrayList ){
                if(thisDate.equals(responseCalendarDto.getLearningDate())) {
                    responseCalendarDto.setSequence(j + 1);
                    responseCalendarDto.setDay(Integer.parseInt(thisDay));
                    responseCalendarDto.setAttendanceStatus(true);
                    arrayList.add(responseCalendarDto);
                    insertFlag = true;
                }
            }

            if(!insertFlag) {
                ResponseCalendarDto responseCalendarDto = new ResponseCalendarDto();
                responseCalendarDto.setSequence(j + 1);
                responseCalendarDto.setDay(Integer.parseInt(thisDay));
                responseCalendarDto.setLearningDate(thisDate);

                arrayList.add(responseCalendarDto);
            }

            j++;
            if(i != 0 && i%6 == 0) {
                responseCalendarDtoArrayList2.add(arrayList);
            }
        }

        System.out.println(responseCalendarDtoArrayList2);


//        return "시작일: " + startDate2 + ", 해당월 말일: " + lastDate + ", 다음달 1일: " + firstDayOfNextMonth + ", 끝일: " + endDate;
        return responseCalendarDtoArrayList2;
    }
}
