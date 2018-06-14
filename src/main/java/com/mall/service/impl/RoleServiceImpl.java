package com.mall.service.impl;

import com.mall.dao.PermissionMapper;
import com.mall.dao.RoleMapper;
import com.mall.dao.RolePermissionMapper;
import com.mall.entity.Permission;
import com.mall.entity.Role;
import com.mall.entity.RolePermission;
import com.mall.entity.RolePermissionExample;
import com.mall.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangtao on 2018/6/14
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Role getRoleByRoleId(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<RolePermission> getRolePermissionByRoleId(Integer roleId) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
        return rolePermissionMapper.selectByExample(rolePermissionExample);
    }

    @Override
    public Permission getPermissionByPermissionId(Integer permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }
}
