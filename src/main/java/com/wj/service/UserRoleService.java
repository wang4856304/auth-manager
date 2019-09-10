package com.wj.service;

import com.wj.entity.po.UserRole;

/**
 * @author jun.wang
 * @title: UserRoleService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/6 9:55
 */
public interface UserRoleService {

    int addUserRole(Long userId, Long roleId);
    int deleteUserRoleByUserId(Long userId);
    UserRole findByUserId(Long userId);
    int updateUserRoleByUserId(Long userId, Long roleId);
}
