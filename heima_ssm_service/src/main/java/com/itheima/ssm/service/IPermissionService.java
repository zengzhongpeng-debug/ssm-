package com.itheima.ssm.service;
import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    void save(Permission permission) throws  Exception;


    public List<Permission> findAll() throws  Exception;

}
