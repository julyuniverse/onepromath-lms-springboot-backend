package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.attendance.ResponseAttendanceCalendarDto;
import com.onepromath.lms.dto.attendance.ResponseAttendanceWeekDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AttendanceMapper {
    // 달력 (출석, 학습량)
    @Select("select login_date2.date                          as learning_date,\n" +
            "       count(learning_data.date)                 as learning_count,\n" +
            "       ifnull(sum(learning_data.solve_cnt), 0)   as total_count,\n" +
            "       ifnull(sum(learning_data.right_cnt), 0)   as right_count,\n" +
            "       ifnull(sum(learning_data.time), 0)        as learning_time_seconds,\n" +
            "       ifnull(sum(learning_data.daily_mode), 0)  as daily_mode_count,\n" +
            "       ifnull(sum(learning_data.free_mode), 0)   as free_mode_count,\n" +
            "       ifnull(sum(learning_data.onepro_mode), 0) as onepro_mode_count,\n" +
            "       ifnull(sum(learning_data.world_mode), 0)  as world_mode_count\n" +
            "from (select *\n" +
            "      from (select date_format(login_date, '%Y-%m-%d') as date\n" +
            "            from LOGIN_LOG\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and login_date >= #{startDate}\n" +
            "              and login_date < #{endDate}\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_DAILY_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_FREE_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_ONEPRO_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_WORLD_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y') login_date\n" +
            "      group by date) login_date2\n" +
            "         left join\n" +
            "     (select 1                                   as daily_mode,\n" +
            "             0                                   as free_mode,\n" +
            "             0                                   as onepro_mode,\n" +
            "             0                                   as world_mode,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_DAILY_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select 0                                   as daily_mode,\n" +
            "             1                                   as free_mode,\n" +
            "             0                                   as onepro_mode,\n" +
            "             0                                   as world_mode,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_FREE_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select 0                                   as daily_mode,\n" +
            "             0                                   as free_mode,\n" +
            "             1                                   as onepro_mode,\n" +
            "             0                                   as world_mode,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_ONEPRO_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select 0                                   as daily_mode,\n" +
            "             0                                   as free_mode,\n" +
            "             0                                   as onepro_mode,\n" +
            "             1                                   as world_mode,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y') learning_data on learning_data.date = login_date2.date\n" +
            "group by login_date2.date;")
    @Results(id = "calendar", value = {
            @Result(property = "learningDate", column = "learning_date"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "totalCount", column = "total_count"),
            @Result(property = "rightCount", column = "right_count"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds"),
            @Result(property = "dailyModeCount", column = "daily_mode_count"),
            @Result(property = "freeModeCount", column = "free_mode_count"),
            @Result(property = "oneproModeCount", column = "onepro_mode_count"),
            @Result(property = "worldModeCount", column = "world_mode_count")
    })
    ArrayList<ResponseAttendanceCalendarDto> calendar(@Param("studentNo") int studentNo, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // 주간 (출석, 학습데이터)
    @Select("select login_date2.date                        as learning_date,\n" +
            "       count(learning_data.date)               as learning_count,\n" +
            "       ifnull(sum(learning_data.solve_cnt), 0) as total_count,\n" +
            "       ifnull(sum(learning_data.right_cnt), 0) as right_count,\n" +
            "       ifnull(sum(learning_data.time), 0)      as learning_time_seconds\n" +
            "from (select *\n" +
            "      from (select date_format(login_date, '%Y-%m-%d') as date\n" +
            "            from LOGIN_LOG\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and login_date >= #{startDate}\n" +
            "              and login_date < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_DAILY_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_FREE_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_ONEPRO_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select date_format(start_time, '%Y-%m-%d') as date\n" +
            "            from RESULT_WORLD_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "              and use_yn = 'y') login_date\n" +
            "      group by date) login_date2\n" +
            "         left join\n" +
            "     (select date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_DAILY_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_FREE_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_ONEPRO_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select date_format(start_time, '%Y-%m-%d') as date,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             time\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < date_format(date_add(#{startDate}, interval 7 day), '%Y-%m-%d')\n" +
            "        and use_yn = 'y') learning_data on learning_data.date = login_date2.date\n" +
            "group by login_date2.date;")
    @Results(id = "week", value = {
            @Result(property = "learningDate", column = "learning_date"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "totalCount", column = "total_count"),
            @Result(property = "rightCount", column = "right_count"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds")
    })
    ArrayList<ResponseAttendanceWeekDto> week(@Param("studentNo") int studentNo, @Param("startDate") String startDate);
}
