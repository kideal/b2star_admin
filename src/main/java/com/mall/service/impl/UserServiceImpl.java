package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.dao.RoleMapper;
import com.mall.dao.UserMapper;
import com.mall.dao.UserRoleMapper;
import com.mall.entity.*;
import com.mall.service.IUserService;
import com.mall.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huangtao on 2018/6/12
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<User> getUsers(HashMap<String, String> parms) {
        PageHelper.startPage(Integer.valueOf(parms.get("page")), Integer.valueOf(parms.get("rows")));
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAddressIdIsNotNull();
        List<User> users = userMapper.selectByExample(userExample);
        return new PageInfo<>(users);
    }

    /**
     * 根据名称或则Email或则手机获取用户活第三方登录
     *
     * @param option
     * @return
     */
    @Override
    public User getUserByUsernameOrEmailOrMobileOrThird(String option) {
        User user = getUserByUsernameOrEmailOrMobile(option);
        if (ObjectUtil.isNotEmpty(user)) {
            return user;
        }
        return null;
    }

    /**
     * 根据名称或则Email或则手机获取用户
     *
     * @param option
     * @return
     */
    @Override
    public User getUserByUsernameOrEmailOrMobile(String option) {
        User user = getUserByUsername(option);
        if (ObjectUtil.isNotEmpty(user)) {
            return user;
        }
        user = getUserByMobile(option);
        if (ObjectUtil.isNotEmpty(user)) {
            return user;
        }
        user = getUserByEmail(option);
        if (ObjectUtil.isNotEmpty(user)) {
            return user;
        }
        return user;
    }

    /**
     * 根据用户查询
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        /**
         * 根据UserName查询
         */
        PageHelper.startPage(1, 1);
        UserExample userNameExample = new UserExample();
        userNameExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userNameExample);
        if (ObjectUtil.isNotEmpty(users)) {
            return users.get(0);
        }
        return null;
    }


    /**
     * 根据 email 进行查询
     *
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        PageHelper.startPage(1, 1);
        UserExample emailNameExample = new UserExample();
        emailNameExample.createCriteria().andEmailEqualTo(email);
        List<User> emailUsers = userMapper.selectByExample(emailNameExample);
        if (ObjectUtil.isNotEmpty(emailUsers)) {
            return emailUsers.get(0);
        }
        return null;
    }

    /**
     * 根据 手机进行查询
     *
     * @param mobile
     * @return
     */
    @Override
    public User getUserByMobile(String mobile) {
        PageHelper.startPage(1, 1);
        UserExample mobileNameExample = new UserExample();
        mobileNameExample.createCriteria().andMobileEqualTo(mobile);
        List<User> mobileUsers = userMapper.selectByExample(mobileNameExample);
        if (ObjectUtil.isNotEmpty(mobileUsers)) {
            return mobileUsers.get(0);
        }
        return null;
    }


    /**
     * 根据UserId 获取Roles
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        List<UserRole> userRoles = findUserRolesByUserId(userId);

        if (ObjectUtil.isNullOrEmpty(userRoles)) {
            return null;
        }

        List<Role> roles = findRolesByUserRoles(userRoles);

        if (ObjectUtil.isNullOrEmpty(roles)) {
            return null;
        }

        return roles;

    }

    public List<UserRole> findUserRolesByUserId(Integer userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);

        return userRoles;
    }

    /**
     * 根据UserRole 获取 Roles
     *
     * @param userRoles
     * @return
     */
    @Override
    public List<Role> findRolesByUserRoles(List<UserRole> userRoles) {
        List<Integer> roleIds = new ArrayList<>();
        userRoles.forEach(userRole -> {
            roleIds.add(userRole.getRoleId());
        });
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleIdIn(roleIds);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }
}
