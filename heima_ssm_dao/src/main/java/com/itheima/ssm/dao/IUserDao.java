package com.itheima.ssm.dao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface IUserDao {

    //查询出用户没有关联的角色信息
    @Select("select * from role where id not in (select roleId from user_role where userId=#{userId})")
    List<Role> findOtherRoles(String userid);

    @Insert("insert into user_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
    //使用@Param是因为mybatis传递多个参数的时候，“values(#{userId},#{roleId}”表示的是userId对象的userId属性和roleId对象的roleId属性
    //而不是我们需要传递的字符串，所以出现"找不到"











    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws  Exception;


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws  Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

}
