package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.student.ResponseStudentDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StudentMapper {
    @Select("select lip.NO student_no, lip.name student_name\n" +
            "from LOGIN_ID_PROFILE lip\n" +
            "         left join LOGIN_ID li on li.NO = lip.LOGIN_ID_NO\n" +
            "where li.SCHOOL_INFO_NO = #{schoolInfoNo}\n" +
            "  and li.SCHOOL_CLASS_NO = #{schoolClassNo}\n" +
            "  and li.school_id_yn = 'y'\n" +
            "  and li.use_yn = 'y';")
    @Results(id = "student", value = {
            @Result(property = "studentNo", column = "student_no"),
            @Result(property = "studentName", column = "student_name")
    })
    ArrayList<ResponseStudentDto> students(@Param("schoolInfoNo") int schoolInfoNo, @Param("schoolClassNo") int schoolClassNo);
}
