package com.mall.service;

import com.mall.entity.Permission;
import com.mall.entity.Role;
import com.mall.entity.RolePermission;

import java.util.List;

/**
 * Created by huangtao on 2018/6/14
 */
public interface IRoleService {
   Role getRoleByRoleId(Integer roleId);

    List<RolePermission> getRolePermissionByRoleId(Integer roleId);

    Permission getPermissionByPermissionId(Integer permissionId);
}
