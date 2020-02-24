package com.flermise.community.mapper;


import com.flermise.community.Model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert(value = "insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl});")
    void insert(User user);

    @Select(value = "select * from user where token = #{token};")
    User findByToken(@Param("token") String token);

    @Select(value = "select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select(value = "select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update(value = "update user  set name = #{name},token = #{token},gmt_modified = #{gmtModified},avatar_url = #{avatarUrl} where id = #{id}")
    void update(User dbUser);
}

