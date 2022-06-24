package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.learning.data.month.ResponseMonthlyLearningDataDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Lee Taesung
 * @since 6/23/22
 */

@Mapper
public interface LearningMapper {
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
}
