package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.entity.Role;
import com.mall.entity.User;
import com.mall.entity.UserRole;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huangtao on 2018/6/12
 */
public interface IUserService {
    PageInfo<User> getUsers(HashMap<String, String> parms);

    User getUserByUsernameOrEmailOrMobileOrThird(String username);

    User getUserByUsernameOrEmailOrMobile(String option);

    User getUserByUsername(String username);


    User getUserByEmail(String email);

    User getUserByMobile(String mobile);

    List<Role> findRolesByUserId(Integer userId);

    List<UserRole> findUserRolesByUserId(Integer userId);

    List<Role> findRolesByUserRoles(List<UserRole> userRoles);
}
