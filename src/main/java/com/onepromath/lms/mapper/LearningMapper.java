package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.learning.data.ResponseLearningDataDto;
import com.onepromath.lms.dto.learning.data.day.DayLearningDataDto;
import com.onepromath.lms.dto.learning.data.level.ResponseLevelAndChapterDataDto;
import com.onepromath.lms.dto.learning.data.level.ResponseLevelDataDto;
import com.onepromath.lms.dto.learning.data.month.ResponseMonthlyLearningDataDto;
import com.onepromath.lms.dto.learning.data.week.ResponseWeeklyLearningDataDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Lee Taesung
 * @since 6/23/22
 */

@Mapper
public interface LearningMapper {

    // 월별 학습 데이터
    @Select("select sum(daily_mode)                                                                           as daily_mode_count,\n" +
            "       sum(free_mode)                                                                            as free_mode_count,\n" +
            "       sum(onepro_mode)                                                                          as onepro_mode_count,\n" +
            "       sum(world_mode)                                                                           as world_mode_count,\n" +
            "       sum(daily_mode) + sum(free_mode) + sum(onepro_mode) + sum(world_mode)                     as learning_count,\n" +
            "       ifnull(round((sum(daily_right_cnt) / sum(daily_solve_cnt)) * 100, 1), 0)                  as daily_accuracy,\n" +
            "       ifnull(round((sum(free_right_cnt) / sum(free_solve_cnt)) * 100, 1), 0)                    as free_accuracy,\n" +
            "       ifnull(round((sum(onepro_right_cnt) / sum(onepro_solve_cnt)) * 100, 1), 0)                as onepro_accuracy,\n" +
            "       ifnull(round((sum(world_right_cnt) / sum(world_solve_cnt)) * 100, 1), 0)                  as world_accuracy,\n" +
            "       ifnull(round((sum(daily_right_cnt) + sum(free_right_cnt) + sum(onepro_right_cnt) + sum(world_right_cnt)) /\n" +
            "                    (sum(daily_solve_cnt) + sum(free_solve_cnt) + sum(onepro_solve_cnt) + sum(world_solve_cnt)) * 100,\n" +
            "                    1), 0)                                                                       as accuracy,\n" +
            "       sum(daily_time)                                                                           as daily_learning_time_seconds,\n" +
            "       sum(free_time)                                                                            as free_learning_time_seconds,\n" +
            "       sum(onepro_time)                                                                          as onepro_learning_time_seconds,\n" +
            "       sum(world_time)                                                                           as world_learning_time_seconds,\n" +
            "       sum(daily_time) + sum(free_time) + sum(onepro_time) +\n" +
            "       sum(world_time)                                                                           as learning_time_seconds,\n" +
            "       round(sum(daily_time) / 60)                                                               as daily_learning_time_minutes,\n" +
            "       round(sum(free_time) / 60)                                                                as free_learning_time_minutes,\n" +
            "       round(sum(onepro_time) / 60)                                                              as onepro_learning_time_minutes,\n" +
            "       round(sum(world_time) / 60)                                                               as world_learning_time_minutes,\n" +
            "       round((sum(daily_time) + sum(free_time) + sum(onepro_time) + sum(world_time)) /\n" +
            "             60)                                                                                 as learning_time_minutes,\n" +
            "       sum(daily_solve_cnt) + sum(free_solve_cnt) + sum(onepro_solve_cnt) + sum(world_solve_cnt) as problem_count,\n" +
            "       concat(date_format(date, '%Y-%m'), '-01')                                                 as date,\n" +
            "       date_format(date, '%Y')                                                                   as year,\n" +
            "       date_format(date, '%m')                                                                   as month\n" +
            "from (select 1                                   as daily_mode,\n" +
            "             0                                   as free_mode,\n" +
            "             0                                   as onepro_mode,\n" +
            "             0                                   as world_mode,\n" +
            "             solve_cnt                           as daily_solve_cnt,\n" +
            "             right_cnt                           as daily_right_cnt,\n" +
            "             time                                as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             solve_cnt                           as free_solve_cnt,\n" +
            "             right_cnt                           as free_right_cnt,\n" +
            "             time                                as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             solve_cnt                           as onepro_solve_cnt,\n" +
            "             right_cnt                           as onepro_right_cnt,\n" +
            "             time                                as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             solve_cnt                           as world_solve_cnt,\n" +
            "             right_cnt                           as world_right_cnt,\n" +
            "             time                                as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y') learning_data\n" +
            "group by date_format(learning_data.date, '%Y-%m');")
    @Results(id = "monthlyLearningData", value = {
            @Result(property = "dailyModeCount", column = "daily_mode_count"),
            @Result(property = "freeModeCount", column = "free_mode_count"),
            @Result(property = "oneproModeCount", column = "onepro_mode_count"),
            @Result(property = "worldModeCount", column = "world_mode_count"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "dailyAccuracy", column = "daily_accuracy"),
            @Result(property = "freeAccuracy", column = "free_accuracy"),
            @Result(property = "oneproAccuracy", column = "onepro_accuracy"),
            @Result(property = "worldAccuracy", column = "world_accuracy"),
            @Result(property = "dailyLearningTimeSeconds", column = "daily_learning_time_seconds"),
            @Result(property = "freeLearningTimeSeconds", column = "free_learning_time_seconds"),
            @Result(property = "oneproLearningTimeSeconds", column = "onepro_learning_time_seconds"),
            @Result(property = "worldLearningTimeSeconds", column = "daily_learning_time_seconds"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds"),
            @Result(property = "dailyLearningTimeMinutes", column = "daily_learning_time_minutes"),
            @Result(property = "freeLearningTimeMinutes", column = "free_learning_time_minutes"),
            @Result(property = "oneproLearningTimeMinutes", column = "onepro_learning_time_minutes"),
            @Result(property = "worldLearningTimeMinutes", column = "world_learning_time_minutes"),
            @Result(property = "learningTimeMinutes", column = "learning_time_minutes"),
            @Result(property = "problemCount", column = "problem_count")
    })
    ArrayList<ResponseMonthlyLearningDataDto> monthlyLearningData(@Param("studentNo") int studentNo, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // 주별 학습 데이터
    @Select("select sum(daily_mode)                                                                           as daily_mode_count,\n" +
            "       sum(free_mode)                                                                            as free_mode_count,\n" +
            "       sum(onepro_mode)                                                                          as onepro_mode_count,\n" +
            "       sum(world_mode)                                                                           as world_mode_count,\n" +
            "       sum(daily_mode) + sum(free_mode) + sum(onepro_mode) + sum(world_mode)                     as learning_count,\n" +
            "       ifnull(round((sum(daily_right_cnt) / sum(daily_solve_cnt)) * 100, 1), 0)                  as daily_accuracy,\n" +
            "       ifnull(round((sum(free_right_cnt) / sum(free_solve_cnt)) * 100, 1), 0)                    as free_accuracy,\n" +
            "       ifnull(round((sum(onepro_right_cnt) / sum(onepro_solve_cnt)) * 100, 1), 0)                as onepro_accuracy,\n" +
            "       ifnull(round((sum(world_right_cnt) / sum(world_solve_cnt)) * 100, 1), 0)                  as world_accuracy,\n" +
            "       ifnull(round((sum(daily_right_cnt) + sum(free_right_cnt) + sum(onepro_right_cnt) + sum(world_right_cnt)) /\n" +
            "                    (sum(daily_solve_cnt) + sum(free_solve_cnt) + sum(onepro_solve_cnt) + sum(world_solve_cnt)) * 100,\n" +
            "                    1), 0)                                                                       as accuracy,\n" +
            "       sum(daily_time)                                                                           as daily_learning_time_seconds,\n" +
            "       sum(free_time)                                                                            as free_learning_time_seconds,\n" +
            "       sum(onepro_time)                                                                          as onepro_learning_time_seconds,\n" +
            "       sum(world_time)                                                                           as world_learning_time_seconds,\n" +
            "       sum(daily_time) + sum(free_time) + sum(onepro_time) +\n" +
            "       sum(world_time)                                                                           as learning_time_seconds,\n" +
            "       round(sum(daily_time) / 60)                                                               as daily_learning_time_minutes,\n" +
            "       round(sum(free_time) / 60)                                                                as free_learning_time_minutes,\n" +
            "       round(sum(onepro_time) / 60)                                                              as onepro_learning_time_minutes,\n" +
            "       round(sum(world_time) / 60)                                                               as world_learning_time_minutes,\n" +
            "       round((sum(daily_time) + sum(free_time) + sum(onepro_time) + sum(world_time)) /\n" +
            "             60)                                                                                 as learning_time_minutes,\n" +
            "       sum(daily_solve_cnt) + sum(free_solve_cnt) + sum(onepro_solve_cnt) + sum(world_solve_cnt) as problem_count\n" +
            "from (select 1                                   as daily_mode,\n" +
            "             0                                   as free_mode,\n" +
            "             0                                   as onepro_mode,\n" +
            "             0                                   as world_mode,\n" +
            "             solve_cnt                           as daily_solve_cnt,\n" +
            "             right_cnt                           as daily_right_cnt,\n" +
            "             time                                as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             solve_cnt                           as free_solve_cnt,\n" +
            "             right_cnt                           as free_right_cnt,\n" +
            "             time                                as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             solve_cnt                           as onepro_solve_cnt,\n" +
            "             right_cnt                           as onepro_right_cnt,\n" +
            "             time                                as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             solve_cnt                           as world_solve_cnt,\n" +
            "             right_cnt                           as world_right_cnt,\n" +
            "             time                                as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y') learning_data;")
    @Results(id = "weeklyLearningData", value = {
            @Result(property = "dailyModeCount", column = "daily_mode_count"),
            @Result(property = "freeModeCount", column = "free_mode_count"),
            @Result(property = "oneproModeCount", column = "onepro_mode_count"),
            @Result(property = "worldModeCount", column = "world_mode_count"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "dailyAccuracy", column = "daily_accuracy"),
            @Result(property = "freeAccuracy", column = "free_accuracy"),
            @Result(property = "oneproAccuracy", column = "onepro_accuracy"),
            @Result(property = "worldAccuracy", column = "world_accuracy"),
            @Result(property = "dailyLearningTimeSeconds", column = "daily_learning_time_seconds"),
            @Result(property = "freeLearningTimeSeconds", column = "free_learning_time_seconds"),
            @Result(property = "oneproLearningTimeSeconds", column = "onepro_learning_time_seconds"),
            @Result(property = "worldLearningTimeSeconds", column = "daily_learning_time_seconds"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds"),
            @Result(property = "dailyLearningTimeMinutes", column = "daily_learning_time_minutes"),
            @Result(property = "freeLearningTimeMinutes", column = "free_learning_time_minutes"),
            @Result(property = "oneproLearningTimeMinutes", column = "onepro_learning_time_minutes"),
            @Result(property = "worldLearningTimeMinutes", column = "world_learning_time_minutes"),
            @Result(property = "learningTimeMinutes", column = "learning_time_minutes"),
            @Result(property = "problemCount", column = "problem_count")
    })
    ResponseWeeklyLearningDataDto weeklyLearningData(@Param("studentNo") int studentNo, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // 레벨 데이터
    @Select("select c2.level, count(*) learning_count\n" +
            "from (select UNIT_NO\n" +
            "      from RESULT_DAILY_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select UNIT_NO\n" +
            "      from RESULT_FREE_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select UNIT_NO\n" +
            "      from RESULT_ONEPRO_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select UNIT_NO\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y') t1\n" +
            "         left join UNIT_2 u2 on t1.UNIT_NO = u2.NO\n" +
            "         left join CHAPTER_2 c2 on u2.CHAPTER_NO = c2.NO\n" +
            "group by c2.level\n" +
            "order by c2.level;")
    @Results(id = "levelData", value = {
            @Result(property = "id", column = "level"),
            @Result(property = "value", column = "learning_count")
    })
    ArrayList<ResponseLevelDataDto> levelData(@Param("studentNo") int studentNo, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // 레벨과 챕터 데이터
    @Select("select @rownum := @rownum + 1 rownum, t2.*\n" +
            "from (select c2.level, c2.chapter_seq, c2.chapter_name_ko, count(c2.chapter_seq) learning_count\n" +
            "      from (select UNIT_NO\n" +
            "            from RESULT_DAILY_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select UNIT_NO\n" +
            "            from RESULT_FREE_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select UNIT_NO\n" +
            "            from RESULT_ONEPRO_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select UNIT_NO\n" +
            "            from RESULT_WORLD_2\n" +
            "            where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "              and start_time >= #{startDate}\n" +
            "              and start_time < #{endDate}\n" +
            "              and use_yn = 'y') t1\n" +
            "               left join UNIT_2 u2 on u2.NO = t1.UNIT_NO\n" +
            "               left join CHAPTER_2 c2 on c2.NO = u2.CHAPTER_NO\n" +
            "      group by c2.level, c2.chapter_seq\n" +
            "      order by count(c2.chapter_seq) desc, c2.level, c2.chapter_seq\n" +
            "      limit 18446744073709551615) t2,\n" +
            "     (SELECT @rownum := 0) tmp_rownum;")
    @Results(id = "levelAndChapterData", value = {
            @Result(property = "id", column = "rownum"),
            @Result(property = "value", column = "learning_count"),
            @Result(property = "chapter", column = "chapter_seq"),
            @Result(property = "chapterName", column = "chapter_name_ko")
    })
    ArrayList<ResponseLevelAndChapterDataDto> levelAndChapterData(@Param("studentNo") int studentNo, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // 학습 데이터
    @Select("select c2.level,\n" +
            "       c2.chapter_seq                                                    as chapter,\n" +
            "       u2.unit_seq                                                       as unit,\n" +
            "       u2.unit_name_ko                                                   as unit_name,\n" +
            "       learning_data.learning_mode,\n" +
            "       learning_data.time                                                as learning_time_seconds,\n" +
            "       learning_data.grade,\n" +
            "       round(learning_data.right_cnt / learning_data.solve_cnt * 100, 1) as accuracy,\n" +
            "       date_format(learning_data.start_time, '%m')                       as month,\n" +
            "       date_format(learning_data.start_time, '%d')                       as day,\n" +
            "       date_format(learning_data.start_time, '%H:%i:%s')                 as start_learning_time\n" +
            "from (select UNIT_NO, '오늘의 학습' as learning_mode, time, result_grade as grade, solve_cnt, right_cnt, start_time\n" +
            "      from RESULT_DAILY_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select UNIT_NO, '자유 학습' as learning_mode, time, result_grade as grade, solve_cnt, right_cnt, start_time\n" +
            "      from RESULT_FREE_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select UNIT_NO,\n" +
            "             '일프로 도전'                              as learning_mode,\n" +
            "             time,\n" +
            "             if(success_cnt > 0, success_cnt, 100) as grade,\n" +
            "             solve_cnt,\n" +
            "             right_cnt,\n" +
            "             start_time\n" +
            "      from RESULT_ONEPRO_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select UNIT_NO, '연산 월드' as learning_mode, time, result_grade as grade, solve_cnt, right_cnt, start_time\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y') learning_data\n" +
            "         left join UNIT_2 u2 on u2.NO = learning_data.UNIT_NO\n" +
            "         left join CHAPTER_2 c2 on u2.CHAPTER_NO = c2.NO\n" +
            "order by learning_data.start_time;")
    @Results(id = "learningData", value = {
            @Result(property = "unitName", column = "unit_name"),
            @Result(property = "learningMode", column = "learning_mode"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds"),
            @Result(property = "startLearningTime", column = "start_learning_time")
    })
    ArrayList<ResponseLearningDataDto> learningData(@Param("studentNo") int studentNo, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // 일별 학습 데이터
    @Select("select learning_data.date                                                         as learning_date,\n" +
            "       sum(daily_mode)                                                            as daily_learning_count,\n" +
            "       sum(free_mode)                                                             as free_learning_count,\n" +
            "       sum(onepro_mode)                                                           as onepro_learning_count,\n" +
            "       sum(world_mode)                                                            as world_learning_count,\n" +
            "       sum(daily_mode) + sum(free_mode) + sum(onepro_mode) + sum(world_mode)      as learning_count,\n" +
            "       ifnull(round((sum(daily_right_cnt) / sum(daily_solve_cnt)) * 100, 1), 0)   as daily_accuracy,\n" +
            "       ifnull(round((sum(free_right_cnt) / sum(free_solve_cnt)) * 100, 1), 0)     as free_accuracy,\n" +
            "       ifnull(round((sum(onepro_right_cnt) / sum(onepro_solve_cnt)) * 100, 1), 0) as onepro_accuracy,\n" +
            "       ifnull(round((sum(world_right_cnt) / sum(world_solve_cnt)) * 100, 1), 0)   as world_accuracy,\n" +
            "       ifnull(round((sum(daily_right_cnt) + sum(free_right_cnt) + sum(onepro_right_cnt) + sum(world_right_cnt)) /\n" +
            "                    (sum(daily_solve_cnt) + sum(free_solve_cnt) + sum(onepro_solve_cnt) + sum(world_solve_cnt)) * 100,\n" +
            "                    1), 0)                                                        as accuracy,\n" +
            "       round(sum(daily_time) / 60)                                                as daily_learning_time_minutes,\n" +
            "       round(sum(free_time) / 60)                                                 as free_learning_time_minutes,\n" +
            "       round(sum(onepro_time) / 60)                                               as onepro_learning_time_minutes,\n" +
            "       round(sum(world_time) / 60)                                                as world_learning_time_minutes,\n" +
            "       round((sum(daily_time) + sum(free_time) + sum(onepro_time) + sum(world_time)) /\n" +
            "             60)                                                                  as learning_time_minutes\n" +
            "from (select 1                                   as daily_mode,\n" +
            "             0                                   as free_mode,\n" +
            "             0                                   as onepro_mode,\n" +
            "             0                                   as world_mode,\n" +
            "             solve_cnt                           as daily_solve_cnt,\n" +
            "             right_cnt                           as daily_right_cnt,\n" +
            "             time                                as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             solve_cnt                           as free_solve_cnt,\n" +
            "             right_cnt                           as free_right_cnt,\n" +
            "             time                                as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             solve_cnt                           as onepro_solve_cnt,\n" +
            "             right_cnt                           as onepro_right_cnt,\n" +
            "             time                                as onepro_time,\n" +
            "             0                                   as world_solve_cnt,\n" +
            "             0                                   as world_right_cnt,\n" +
            "             0                                   as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
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
            "             0                                   as daily_solve_cnt,\n" +
            "             0                                   as daily_right_cnt,\n" +
            "             0                                   as daily_time,\n" +
            "             0                                   as free_solve_cnt,\n" +
            "             0                                   as free_right_cnt,\n" +
            "             0                                   as free_time,\n" +
            "             0                                   as onepro_solve_cnt,\n" +
            "             0                                   as onepro_right_cnt,\n" +
            "             0                                   as onepro_time,\n" +
            "             solve_cnt                           as world_solve_cnt,\n" +
            "             right_cnt                           as world_right_cnt,\n" +
            "             time                                as world_time,\n" +
            "             date_format(start_time, '%Y-%m-%d') as date\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO = #{studentNo}\n" +
            "        and start_time >= #{startDate}\n" +
            "        and start_time < #{endDate}\n" +
            "        and use_yn = 'y') learning_data\n" +
            "group by learning_data.date;")
    @Results(id = "dailyLearningData", value = {
            @Result(property = "learningDate", column = "learning_date"),
            @Result(property = "dailyLearningCount", column = "daily_learning_count"),
            @Result(property = "freeLearningCount", column = "free_learning_count"),
            @Result(property = "oneproLearningCount", column = "onepro_learning_count"),
            @Result(property = "worldLearningCount", column = "world_learning_count"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "dailyAccuracy", column = "daily_accuracy"),
            @Result(property = "freeAccuracy", column = "free_accuracy"),
            @Result(property = "oneproAccuracy", column = "onepro_accuracy"),
            @Result(property = "worldAccuracy", column = "world_accuracy"),
            @Result(property = "dailyLearningTimeMinutes", column = "daily_learning_time_minutes"),
            @Result(property = "freeLearningTimeMinutes", column = "free_learning_time_minutes"),
            @Result(property = "oneproLearningTimeMinutes", column = "onepro_learning_time_minutes"),
            @Result(property = "worldLearningTimeMinutes", column = "world_learning_time_minutes"),
            @Result(property = "learningTimeMinutes", column = "learning_time_minutes")
    })
    ArrayList<DayLearningDataDto> dailyLearningData(@Param("studentNo") int studentNo, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
