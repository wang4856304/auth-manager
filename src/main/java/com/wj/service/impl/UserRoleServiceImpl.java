package com.wj.service.impl;

import com.wj.entity.po.UserRole;
import com.wj.repository.UserRoleRepository;
import com.wj.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jun.wang
 * @title: UserRoleServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/6 9:56
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public int addUserRole(Long userId, Long roleId) {
        UserRole userRole = userRoleRepository.findByUserId(userId);
        if (userRole == null) {
            UserRole userRoleX = new UserRole();
            userRoleX.setUserId(userId);
            userRoleX.setRoleId(roleId);
            userRoleRepository.save(userRoleX);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteUserRoleByUserId(Long userId) {
        userRoleRepository.deleteByUserId(userId);
        return 1;
    }

    @Override
    public UserRole findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public int updateUserRoleByUserId(Long userId, Long roleId) {
        deleteUserRoleByUserId(userId);
        addUserRole(userId, roleId);
        return 0;
    }
}
