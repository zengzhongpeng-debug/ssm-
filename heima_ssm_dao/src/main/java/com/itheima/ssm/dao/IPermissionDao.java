package com.itheima.ssm.dao;
import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface IPermissionDao {

    @Insert("insert into permission(permissionName,url) values(#(permissionName),#(url))")
    void save(Permission permission) throws Exception;


    @Select("select * from permission")
    List<Permission> findAll() throws Exception;



    //查询与role关联的所有的权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;


}
