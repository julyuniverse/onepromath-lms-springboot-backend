package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.classes.ResponseClassDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ClassMapper {
    @Select("select NO school_class_no, name class_name\n" +
            "from SCHOOL_CLASS\n" +
            "where school_no = #{schoolInfoNo}\n" +
            "  and year = #{schoolYear}\n" +
            "  and use_yn = 'y';")
    @Results(id = "class", value = {
            @Result(property = "schoolClassNo", column = "school_class_no"),
            @Result(property = "className", column = "class_name")
    })
    ArrayList<ResponseClassDto> classes(@Param("schoolInfoNo") int schoolInfoNo, @Param("schoolYear") int schoolYear);
}
