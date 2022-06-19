package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.student.average.ResponseAverageStudentDto;
import com.onepromath.lms.dto.student.weekly.ResponseWeeklyStudentDto;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Mapper
public interface StudentMapper {
    // 주간 모든 학생
    @Select("select @rownum := @rownum + 1 sequence, t1.*\n" +
            "from (select students.student_no,\n" +
            "             students.student_name,\n" +
            "             ifnull(sum(learning_data.time), 0)            as learning_time_seconds,\n" +
            "             ifnull(round(avg(learning_data.accuracy)), 0) as accuracy,\n" +
            "             count(learning_data.profile_number)           as learning_count,\n" +
            "             (select count(*)\n" +
            "              from RESULT_DAILY_2 scalar_rd2\n" +
            "              where scalar_rd2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rd2.start_time, '%Y-%m-%d') = #{startDate}\n" +
            "                and scalar_rd2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_FREE_2 scalar_rf2\n" +
            "              where scalar_rf2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rf2.start_time, '%Y-%m-%d') = #{startDate}\n" +
            "                and scalar_rf2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_ONEPRO_2 scalar_ro2\n" +
            "              where scalar_ro2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_ro2.start_time, '%Y-%m-%d') = #{startDate}\n" +
            "                and scalar_ro2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_WORLD_2 scalar_rw2\n" +
            "              where scalar_rw2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rw2.start_time, '%Y-%m-%d') = #{startDate}\n" +
            "                and scalar_rw2.use_yn = 'y')               as monday_learning_count,\n" +
            "             (select count(*)\n" +
            "              from RESULT_DAILY_2 scalar_rd2\n" +
            "              where scalar_rd2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rd2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 1 day)\n" +
            "                and scalar_rd2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_FREE_2 scalar_rf2\n" +
            "              where scalar_rf2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rf2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 1 day)\n" +
            "                and scalar_rf2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_ONEPRO_2 scalar_ro2\n" +
            "              where scalar_ro2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_ro2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 1 day)\n" +
            "                and scalar_ro2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_WORLD_2 scalar_rw2\n" +
            "              where scalar_rw2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rw2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 1 day)\n" +
            "                and scalar_rw2.use_yn = 'y')               as tuesday_learning_count,\n" +
            "             (select count(*)\n" +
            "              from RESULT_DAILY_2 scalar_rd2\n" +
            "              where scalar_rd2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rd2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 2 day)\n" +
            "                and scalar_rd2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_FREE_2 scalar_rf2\n" +
            "              where scalar_rf2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rf2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 2 day)\n" +
            "                and scalar_rf2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_ONEPRO_2 scalar_ro2\n" +
            "              where scalar_ro2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_ro2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 2 day)\n" +
            "                and scalar_ro2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_WORLD_2 scalar_rw2\n" +
            "              where scalar_rw2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rw2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 2 day)\n" +
            "                and scalar_rw2.use_yn = 'y')               as wednesday_learning_count,\n" +
            "             (select count(*)\n" +
            "              from RESULT_DAILY_2 scalar_rd2\n" +
            "              where scalar_rd2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rd2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 3 day)\n" +
            "                and scalar_rd2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_FREE_2 scalar_rf2\n" +
            "              where scalar_rf2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rf2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 3 day)\n" +
            "                and scalar_rf2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_ONEPRO_2 scalar_ro2\n" +
            "              where scalar_ro2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_ro2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 3 day)\n" +
            "                and scalar_ro2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_WORLD_2 scalar_rw2\n" +
            "              where scalar_rw2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rw2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 3 day)\n" +
            "                and scalar_rw2.use_yn = 'y')               as thursday_learning_count,\n" +
            "             (select count(*)\n" +
            "              from RESULT_DAILY_2 scalar_rd2\n" +
            "              where scalar_rd2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rd2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 4 day)\n" +
            "                and scalar_rd2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_FREE_2 scalar_rf2\n" +
            "              where scalar_rf2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rf2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 4 day)\n" +
            "                and scalar_rf2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_ONEPRO_2 scalar_ro2\n" +
            "              where scalar_ro2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_ro2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 4 day)\n" +
            "                and scalar_ro2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_WORLD_2 scalar_rw2\n" +
            "              where scalar_rw2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rw2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 4 day)\n" +
            "                and scalar_rw2.use_yn = 'y')               as friday_learning_count,\n" +
            "             (select count(*)\n" +
            "              from RESULT_DAILY_2 scalar_rd2\n" +
            "              where scalar_rd2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rd2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 5 day)\n" +
            "                and scalar_rd2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_FREE_2 scalar_rf2\n" +
            "              where scalar_rf2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rf2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 5 day)\n" +
            "                and scalar_rf2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_ONEPRO_2 scalar_ro2\n" +
            "              where scalar_ro2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_ro2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 5 day)\n" +
            "                and scalar_ro2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_WORLD_2 scalar_rw2\n" +
            "              where scalar_rw2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rw2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 5 day)\n" +
            "                and scalar_rw2.use_yn = 'y')               as saturday_learning_count,\n" +
            "             (select count(*)\n" +
            "              from RESULT_DAILY_2 scalar_rd2\n" +
            "              where scalar_rd2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rd2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 6 day)\n" +
            "                and scalar_rd2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_FREE_2 scalar_rf2\n" +
            "              where scalar_rf2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rf2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 6 day)\n" +
            "                and scalar_rf2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_ONEPRO_2 scalar_ro2\n" +
            "              where scalar_ro2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_ro2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 6 day)\n" +
            "                and scalar_ro2.use_yn = 'y') +\n" +
            "             (select count(*)\n" +
            "              from RESULT_WORLD_2 scalar_rw2\n" +
            "              where scalar_rw2.LOGIN_ID_PROFILE_NO = students.student_no\n" +
            "                and date_format(scalar_rw2.start_time, '%Y-%m-%d') = date_add(#{startDate}, interval 6 day)\n" +
            "                and scalar_rw2.use_yn = 'y')               as sunday_learning_count\n" +
            "      from (select lip.NO student_no, lip.name student_name\n" +
            "            from LOGIN_ID_PROFILE lip\n" +
            "                     left join LOGIN_ID li on li.NO = lip.LOGIN_ID_NO\n" +
            "            where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "              and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "              and li.school_id_yn = 'y'\n" +
            "              and li.use_yn = 'y') students\n" +
            "               left join (select sub_learning_data.*\n" +
            "                          from (select lip.NO student_no\n" +
            "                                from LOGIN_ID_PROFILE lip\n" +
            "                                         left join LOGIN_ID li on li.NO = lip.LOGIN_ID_NO\n" +
            "                                where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                  and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                  and li.school_id_yn = 'y'\n" +
            "                                  and li.use_yn = 'y') sub_students\n" +
            "                                   left join (select LOGIN_ID_PROFILE_NO profile_number, time, accuracy\n" +
            "                                              from RESULT_DAILY_2\n" +
            "                                              where date_format(start_time, '%Y-%m-%d') >= #{startDate}\n" +
            "                                                and date_format(start_time, '%Y-%m-%d') <= #{endDate}\n" +
            "                                                and use_yn = 'y') sub_learning_data\n" +
            "                                             on sub_learning_data.profile_number = sub_students.student_no\n" +
            "                          where sub_learning_data.profile_number is not null\n" +
            "                          union all\n" +
            "                          select sub_learning_data.*\n" +
            "                          from (select lip.NO student_no\n" +
            "                                from LOGIN_ID_PROFILE lip\n" +
            "                                         left join LOGIN_ID li on li.NO = lip.LOGIN_ID_NO\n" +
            "                                where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                  and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                  and li.school_id_yn = 'y'\n" +
            "                                  and li.use_yn = 'y') sub_students\n" +
            "                                   left join (select LOGIN_ID_PROFILE_NO profile_number, time, accuracy\n" +
            "                                              from RESULT_FREE_2\n" +
            "                                              where date_format(start_time, '%Y-%m-%d') >= #{startDate}\n" +
            "                                                and date_format(start_time, '%Y-%m-%d') <= #{endDate}\n" +
            "                                                and use_yn = 'y') sub_learning_data\n" +
            "                                             on sub_learning_data.profile_number = sub_students.student_no\n" +
            "                          where sub_learning_data.profile_number is not null\n" +
            "                          union all\n" +
            "                          select sub_learning_data.*\n" +
            "                          from (select lip.NO student_no\n" +
            "                                from LOGIN_ID_PROFILE lip\n" +
            "                                         left join LOGIN_ID li on li.NO = lip.LOGIN_ID_NO\n" +
            "                                where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                  and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                  and li.school_id_yn = 'y'\n" +
            "                                  and li.use_yn = 'y') sub_students\n" +
            "                                   left join (select LOGIN_ID_PROFILE_NO profile_number, time, accuracy\n" +
            "                                              from RESULT_ONEPRO_2\n" +
            "                                              where date_format(start_time, '%Y-%m-%d') >= #{startDate}\n" +
            "                                                and date_format(start_time, '%Y-%m-%d') <= #{endDate}\n" +
            "                                                and use_yn = 'y') sub_learning_data\n" +
            "                                             on sub_learning_data.profile_number = sub_students.student_no\n" +
            "                          where sub_learning_data.profile_number is not null\n" +
            "                          union all\n" +
            "                          select sub_learning_data.*\n" +
            "                          from (select lip.NO student_no\n" +
            "                                from LOGIN_ID_PROFILE lip\n" +
            "                                         left join LOGIN_ID li on li.NO = lip.LOGIN_ID_NO\n" +
            "                                where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                  and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                  and li.school_id_yn = 'y'\n" +
            "                                  and li.use_yn = 'y') sub_students\n" +
            "                                   left join (select LOGIN_ID_PROFILE_NO profile_number, time, accuracy\n" +
            "                                              from RESULT_WORLD_2\n" +
            "                                              where date_format(start_time, '%Y-%m-%d') >= #{startDate}\n" +
            "                                                and date_format(start_time, '%Y-%m-%d') <= #{endDate}\n" +
            "                                                and use_yn = 'y') sub_learning_data\n" +
            "                                             on sub_learning_data.profile_number = sub_students.student_no\n" +
            "                          where sub_learning_data.profile_number is not null) learning_data\n" +
            "                         on learning_data.profile_number = students.student_no\n" +
            "      group by students.student_no) t1,\n" +
            "     (select @rownum := 0) rownum\n" +
            "order by t1.${sort} ${order};")
    @Results(id = "weeklyStudents", value = {
            @Result(property = "studentNo", column = "student_no"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds"),
            @Result(property = "accuracy", column = "accuracy"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "mondayLearningCount", column = "monday_learning_count"),
            @Result(property = "tuesdayLearningCount", column = "tuesday_learning_count"),
            @Result(property = "wednesdayLearningCount", column = "wednesday_learning_count"),
            @Result(property = "thursdayLearningCount", column = "thursday_learning_count"),
            @Result(property = "fridayLearningCount", column = "friday_learning_count"),
            @Result(property = "saturdayLearningCount", column = "saturday_learning_count"),
            @Result(property = "sundayLearningCount", column = "sunday_learning_count")
    })
    ArrayList<ResponseWeeklyStudentDto> weeklyStudents(@Param("schoolInfoNo") int schoolInfoNo, @Param("schoolClassNo") int schoolClassNo, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sort") String sort, @Param("order") String order);

    // 평균 모든 학생
    @Select("select *\n" +
            "from (select student.student_no,\n" +
            "             student.student_name,\n" +
            "             count(learning_data.student_no)                                                           learning_count,\n" +
            "             ifnull(round(sum(learning_data.right_cnt) / sum(learning_data.solve_cnt) * 100, 1), 0) as accuracy,\n" +
            "             ifnull(sum(learning_data.time), 0)                                                     as learning_time_seconds\n" +
            "      from (select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "            from RESULT_DAILY_2\n" +
            "            where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                          from LOGIN_ID li\n" +
            "                                                   left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                          where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                            and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                            and li.school_id_yn = 'y'\n" +
            "                                            and li.use_yn = 'y'\n" +
            "                                            and lip.use_yn = 'y')\n" +
            "              and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "            from RESULT_FREE_2\n" +
            "            where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                          from LOGIN_ID li\n" +
            "                                                   left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                          where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                            and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                            and li.school_id_yn = 'y'\n" +
            "                                            and li.use_yn = 'y'\n" +
            "                                            and lip.use_yn = 'y')\n" +
            "              and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "            from RESULT_ONEPRO_2\n" +
            "            where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                          from LOGIN_ID li\n" +
            "                                                   left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                          where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                            and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                            and li.school_id_yn = 'y'\n" +
            "                                            and li.use_yn = 'y'\n" +
            "                                            and lip.use_yn = 'y')\n" +
            "              and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "              and use_yn = 'y'\n" +
            "            union all\n" +
            "            select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "            from RESULT_WORLD_2\n" +
            "            where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                          from LOGIN_ID li\n" +
            "                                                   left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                          where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                            and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                            and li.school_id_yn = 'y'\n" +
            "                                            and li.use_yn = 'y'\n" +
            "                                            and lip.use_yn = 'y')\n" +
            "              and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "              and use_yn = 'y') learning_data\n" +
            "               right join\n" +
            "           (select lip.NO student_no, lip.name student_name\n" +
            "            from LOGIN_ID li\n" +
            "                     left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "            where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "              and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "              and li.school_id_yn = 'y'\n" +
            "              and li.use_yn = 'y'\n" +
            "              and lip.use_yn = 'y') student on student.student_no = learning_data.student_no\n" +
            "      group by student.student_no) students\n" +
            "order by students.${sort} ${order};")
    @Results(id = "averageStudents", value = {
            @Result(property = "studentNo", column = "student_no"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "accuracy", column = "accuracy"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds")
    })
    ArrayList<ResponseAverageStudentDto> averageStudents(@Param("schoolInfoNo") int schoolInfoNo, @Param("schoolClassNo") int schoolClassNo, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sort") String sort, @Param("order") String order);
}
