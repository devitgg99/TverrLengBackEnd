package org.example.tverleng.repository;

import org.apache.ibatis.annotations.*;
import org.example.tverleng.model.entities.AppUser;
import org.example.tverleng.model.request.AppUserRequest;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface AppUserRepository {

    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "full_name")
    })
    @Select("""
                SELECT * FROM tverlengdb.public.app_users
                WHERE email = #{email};
            """)
    AppUser getUserByEmail(String email);

    @Select("""
        INSERT INTO tverlengdb.public.app_users
        values(default, #{req.fullName} ,#{req.email}, #{req.password})
        returning *;
    """)
    @ResultMap("appUserMapper")
    AppUser register(@Param("req") AppUserRequest appUserRequest);
}
