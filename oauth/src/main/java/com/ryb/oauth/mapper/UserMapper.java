package com.ryb.oauth.mapper;

import com.ryb.core.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 常坤
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 校验用户
     *
     * @param user
     * @return
     */
    User authUser(User user);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    Integer register(User user);

    /**
     * 验证用户是否已注册
     *
     * @param user
     * @return
     */
    @Select("SELECT COUNT(1) FROM user WHERE user_mobile = #{user.userMobile} OR user_mail = #{user.userMail} AND status = 1")
    int isRegister(@Param("user") User user);
}
