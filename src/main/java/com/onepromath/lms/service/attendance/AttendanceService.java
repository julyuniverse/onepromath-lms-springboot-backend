package com.onepromath.lms.service.attendance;

import com.onepromath.lms.dto.attendance.ResponseAttendanceCalendarDto;
import com.onepromath.lms.dto.attendance.ResponseAttendanceWeekDto;
import com.onepromath.lms.mapper.AttendanceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceMapper attendanceMapper;

    public AttendanceService(AttendanceMapper attendanceMapper) {
        this.attendanceMapper = attendanceMapper;
    }

    // 달력 (출석, 학습량)
    public ArrayList<ArrayList<ResponseAttendanceCalendarDto>> calendar(int studentNo, String startDate) throws ParseException {
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

        // 당월 ArrayList 만들기.
        ArrayList<ResponseAttendanceCalendarDto> responseAttendanceCalendarDtoArrayList = attendanceMapper.calendar(studentNo, startDate, firstDayOfNextMonth);

        ArrayList<ArrayList<ResponseAttendanceCalendarDto>> responseCalendarDtoArrayList2 = new ArrayList<>();

        ArrayList<ResponseAttendanceCalendarDto> arrayList = null;
        int j = 0;
        boolean insertFlag;
        SimpleDateFormat format2 = new SimpleDateFormat("dd");
        for (int i = 0; i <= days; i++) {
            if (i % 7 == 0) { // 7로 나눠서 0이 될 때마다 ArrayList 생성
                arrayList = new ArrayList<>();
                j = 0;
            }
            insertFlag = false;
            Calendar calendar5 = Calendar.getInstance();
            calendar5.setTime(date3);
            calendar5.add(Calendar.DATE, i);
            String thisDate = format.format(calendar5.getTime());
            String thisDay = format2.format(calendar5.getTime());

            for (ResponseAttendanceCalendarDto responseAttendanceCalendarDto : responseAttendanceCalendarDtoArrayList) {
                if (thisDate.equals(responseAttendanceCalendarDto.getLearningDate())) {
                    responseAttendanceCalendarDto.setSequence(j + 1);
                    responseAttendanceCalendarDto.setDay(Integer.parseInt(thisDay));
                    responseAttendanceCalendarDto.setAttendanceStatus(true);
                    arrayList.add(responseAttendanceCalendarDto);
                    insertFlag = true;
                }
            }

            if (!insertFlag) {
                ResponseAttendanceCalendarDto responseAttendanceCalendarDto = new ResponseAttendanceCalendarDto();
                responseAttendanceCalendarDto.setSequence(j + 1);
                responseAttendanceCalendarDto.setDay(Integer.parseInt(thisDay));
                responseAttendanceCalendarDto.setLearningDate(thisDate);

                arrayList.add(responseAttendanceCalendarDto);
            }

            j++;
            if (i != 0 && i % 6 == 0) {
                responseCalendarDtoArrayList2.add(arrayList);
            }
        }

        return responseCalendarDtoArrayList2;
    }

    // 주간 (출석, 학습량)
    public ArrayList<ResponseAttendanceWeekDto> week(int studentNo, String startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd");

        Date date = format.parse(startDate);

        ArrayList<ResponseAttendanceWeekDto> responseAttendanceWeekDtoArrayList = attendanceMapper.week(studentNo, startDate);
        ArrayList<ResponseAttendanceWeekDto> responseAttendanceWeekDtoArrayList2 = new ArrayList<>();

        boolean insertFlag;
        for (int i = 0; i < 7; i++) {
            insertFlag = false;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, +i);
            String thisDate = format.format(calendar.getTime());
            String thisDay = format2.format(calendar.getTime());
            String dayOfWeek = "";
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    dayOfWeek = "일";
                    break;
                case 2:
                    dayOfWeek = "월";
                    break;
                case 3:
                    dayOfWeek = "화";
                    break;
                case 4:
                    dayOfWeek = "수";
                    break;
                case 5:
                    dayOfWeek = "목";
                    break;
                case 6:
                    dayOfWeek = "금";
                    break;
                case 7:
                    dayOfWeek = "토";
                    break;
            }

            for (ResponseAttendanceWeekDto responseAttendanceWeekDto : responseAttendanceWeekDtoArrayList) { // 가져온 데이터와 날짜 비교 후 같으면 삽입
                if (Objects.equals(responseAttendanceWeekDto.getLearningDate(), thisDate)) {
                    responseAttendanceWeekDto.setAttendanceStatus(true);
                    responseAttendanceWeekDto.setDay(Integer.parseInt(thisDay));
                    responseAttendanceWeekDto.setDayOfWeek(dayOfWeek);
                    responseAttendanceWeekDtoArrayList2.add(responseAttendanceWeekDto);
                    insertFlag = true;
                    break;
                }
            }

            if (!insertFlag) {
                ResponseAttendanceWeekDto responseAttendanceWeekDto = new ResponseAttendanceWeekDto();
                responseAttendanceWeekDto.setLearningDate(thisDate);
                responseAttendanceWeekDto.setDay(Integer.parseInt(thisDay));
                responseAttendanceWeekDto.setDayOfWeek(dayOfWeek);
                responseAttendanceWeekDtoArrayList2.add(responseAttendanceWeekDto);
            }
        }

        return responseAttendanceWeekDtoArrayList2;
    }
}
