package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.user.login.ResponseUserLoginDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper { // 유저
    @Select("select tm.name teacher_name, tm.school_no school_info_no, si.name school_name\n" +
            "from TEACHER_MEMBER tm\n" +
            "         left join SCHOOL_INFO si on si.NO = tm.school_no\n" +
            "where id = #{id}\n" +
            "  and pw = #{pw};")
    @Results(id = "login", value = {
            @Result(property = "teacherName", column = "teacher_name"),
            @Result(property = "schoolInfoNo", column = "school_info_no"),
            @Result(property = "schoolName", column = "school_name")
    })
    ResponseUserLoginDto login(@Param("id") String id, @Param("pw") String pw);
}
