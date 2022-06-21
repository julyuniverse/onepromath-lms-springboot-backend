package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.classes.averageclass.ResponseAverageClassDto;
import com.onepromath.lms.dto.classes.classes.ResponseClassDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ClassMapper {
    // 반 목록
    @Select("select NO                   as school_class_no,\n" +
            "       name                 as class_name,\n" +
            "       (select count(*)\n" +
            "        from LOGIN_ID\n" +
            "        where SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "          and SCHOOL_CLASS_NO = SCHOOL_CLASS.NO\n" +
            "          and school_id_yn = 'y'\n" +
            "          and use_yn = 'y') as student_count\n" +
            "from SCHOOL_CLASS\n" +
            "where school_no = #{schoolInfoNo}\n" +
            "  and year = #{schoolYear}\n" +
            "  and use_yn = 'y'\n" +
            "order by class_name;")
    @Results(id = "class", value = {
            @Result(property = "schoolClassNo", column = "school_class_no"),
            @Result(property = "className", column = "class_name"),
            @Result(property = "studentCount", column = "student_count")
    })
    ArrayList<ResponseClassDto> classes(@Param("schoolInfoNo") int schoolInfoNo, @Param("schoolYear") int schoolYear);

    // 반 목록
    @Select("select NO                   as school_class_no,\n" +
            "       name                 as class_name,\n" +
            "       (select count(*)\n" +
            "        from LOGIN_ID\n" +
            "        where SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "          and SCHOOL_CLASS_NO = SCHOOL_CLASS.NO\n" +
            "          and school_id_yn = 'y'\n" +
            "          and use_yn = 'y') as student_count\n" +
            "from SCHOOL_CLASS\n" +
            "where school_no = #{schoolInfoNo}\n" +
            "  and use_yn = 'y'\n" +
            "order by class_name;\n")
    @ResultMap("class")
    ArrayList<ResponseClassDto> classes2(@Param("schoolInfoNo") int schoolInfoNo);

    // 반 목록
    @Select("select NO school_class_no, name class_name\n" +
            "from LOGIN_ID\n" +
            "where SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "  and (isnull(SCHOOL_CLASS_NO) or SCHOOL_CLASS_NO = '')\n" +
            "  and school_id_yn = 'y'\n" +
            "  and use_yn = 'y'\n" +
            "order by name;")
    @ResultMap("class")
    ArrayList<ResponseClassDto> classes3(@Param("schoolInfoNo") int schoolInfoNo);

    // 평균 반
    @Select("select ifnull(round(count(learning_data.student_no) / (select count(*) cnt\n" +
            "                                                       from LOGIN_ID li\n" +
            "                                                                left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                                       where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                                         and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                                         and li.school_id_yn = 'y'\n" +
            "                                                         and li.use_yn = 'y'\n" +
            "                                                         and lip.use_yn = 'y'), 1), 0)        as learning_count,\n" +
            "       ifnull(round(sum(learning_data.right_cnt) / sum(learning_data.solve_cnt) * 100, 1), 0) as accuracy,\n" +
            "       ifnull(round(sum(learning_data.time) / (select count(*) cnt\n" +
            "                                               from LOGIN_ID li\n" +
            "                                                        left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                               where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                                 and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                                 and li.school_id_yn = 'y'\n" +
            "                                                 and li.use_yn = 'y'\n" +
            "                                                 and lip.use_yn = 'y')), 0)                   as learning_time_seconds\n" +
            "from (select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "      from RESULT_DAILY_2\n" +
            "      where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                    from LOGIN_ID li\n" +
            "                                             left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                    where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                      and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                      and li.school_id_yn = 'y'\n" +
            "                                      and li.use_yn = 'y'\n" +
            "                                      and lip.use_yn = 'y')\n" +
            "        and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "      from RESULT_FREE_2\n" +
            "      where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                    from LOGIN_ID li\n" +
            "                                             left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                    where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                      and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                      and li.school_id_yn = 'y'\n" +
            "                                      and li.use_yn = 'y'\n" +
            "                                      and lip.use_yn = 'y')\n" +
            "        and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "      from RESULT_ONEPRO_2\n" +
            "      where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                    from LOGIN_ID li\n" +
            "                                             left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                    where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                      and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                      and li.school_id_yn = 'y'\n" +
            "                                      and li.use_yn = 'y'\n" +
            "                                      and lip.use_yn = 'y')\n" +
            "        and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "        and use_yn = 'y'\n" +
            "      union all\n" +
            "      select LOGIN_ID_PROFILE_NO student_no, solve_cnt, right_cnt, time\n" +
            "      from RESULT_WORLD_2\n" +
            "      where LOGIN_ID_PROFILE_NO in (select lip.NO\n" +
            "                                    from LOGIN_ID li\n" +
            "                                             left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "                                    where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "                                      and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "                                      and li.school_id_yn = 'y'\n" +
            "                                      and li.use_yn = 'y'\n" +
            "                                      and lip.use_yn = 'y')\n" +
            "        and date_format(start_time, '%Y-%m-%d') between #{startDate} and #{endDate}\n" +
            "        and use_yn = 'y') learning_data\n" +
            "         right join\n" +
            "     (select lip.NO student_no\n" +
            "      from LOGIN_ID li\n" +
            "               left join LOGIN_ID_PROFILE lip on li.NO = lip.LOGIN_ID_NO\n" +
            "      where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "        and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "        and li.school_id_yn = 'y'\n" +
            "        and li.use_yn = 'y'\n" +
            "        and lip.use_yn = 'y') student on student.student_no = learning_data.student_no;")
    @Results(id = "averageClass", value = {
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "learningTimeSeconds", column = "learning_time_seconds")
    })
    ResponseAverageClassDto averageClass(@Param("schoolInfoNo") int schoolInfoNo, @Param("schoolClassNo") int schoolClassNo, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
