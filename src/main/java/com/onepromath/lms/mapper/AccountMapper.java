package com.onepromath.lms.mapper;

import com.onepromath.lms.dto.account.info.ProfileInfoDto;
import com.onepromath.lms.dto.account.info.UserInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Lee Taesung
 * @since 7/4/22
 */

@Mapper
public interface AccountMapper {
    // 유저 정보
    @Select("select count(*)                           as existence_status,\n" +
            "       ifnull(li.NO, 0)                   as user_no,\n" +
            "       if(li.use_yn = 'y', 1, 0)          as use_status,\n" +
            "       if(li.paid_yn = 'y', 1, 0)         as paid_account_status,\n" +
            "       if((select count(*)\n" +
            "           from LOGIN_ID_ACTIVE\n" +
            "           where LOGIN_ID_NO = li.NO\n" +
            "             and e_date >= date_format(now(), '%Y-%m-%d')\n" +
            "             and use_yn = 'y') > 0, 1, 0) as paid_status,\n" +
            "       if(li.school_id_yn = 'y', 1, 0)    as school_account_status\n" +
            "from LOGIN_ID li\n" +
            "where li.NO = #{userNo};")
    @Results(id = "userInfo", value = {
            @Result(property = "existenceStatus", column = "existence_status"),
            @Result(property = "userNo", column = "user_no"),
            @Result(property = "useStatus", column = "use_status"),
            @Result(property = "paidAccountStatus", column = "paid_account_status"),
            @Result(property = "paidStatus", column = "paid_status"),
            @Result(property = "schoolAccountStatus", column = "school_account_status")
    })
    UserInfoDto userInfo(@Param("userNo") int userNo);

    // 유저 정보2
    @Select("select count(li.NO)                       as existence_status,\n" +
            "       ifnull(li.NO, 0)                   as user_no,\n" +
            "       if(li.use_yn = 'y', 1, 0)          as use_status,\n" +
            "       if(li.paid_yn = 'y', 1, 0)         as paid_account_status,\n" +
            "       if((select count(*)\n" +
            "           from LOGIN_ID_ACTIVE\n" +
            "           where LOGIN_ID_NO = li.NO\n" +
            "             and e_date >= date_format(now(), '%Y-%m-%d')\n" +
            "             and use_yn = 'y') > 0, 1, 0) as paid_status,\n" +
            "       if(li.school_id_yn = 'y', 1, 0)    as school_account_status\n" +
            "FROM LOGIN_ID_PROFILE lip\n" +
            "         left join LOGIN_ID li on li.NO = lip.LOGIN_ID_NO\n" +
            "where lip.NO = #{profileNo};")
    @Results(id = "userInfo2", value = {
            @Result(property = "existenceStatus", column = "existence_status"),
            @Result(property = "userNo", column = "user_no"),
            @Result(property = "useStatus", column = "use_status"),
            @Result(property = "paidAccountStatus", column = "paid_account_status"),
            @Result(property = "paidStatus", column = "paid_status"),
            @Result(property = "schoolAccountStatus", column = "school_account_status")
    })
    UserInfoDto userInfo2(@Param("profileNo") int profileNo);

    // 프로필 정보
    @Select("select NO as profile_no, name as profile_name\n" +
            "from LOGIN_ID_PROFILE\n" +
            "where LOGIN_ID_NO = #{userNo}\n" +
            "  and use_yn = 'y';")
    @Results(id = "profileInfo", value = {
            @Result(property = "profileNo", column = "profile_no"),
            @Result(property = "profileName", column = "profile_name")
    })
    ArrayList<ProfileInfoDto> profileInfo(@Param("userNo") int userNo);
}
