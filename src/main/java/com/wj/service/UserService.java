package com.wj.service;

import com.wj.entity.po.User;
import com.wj.utils.Page;

import java.util.List;

/**
 * @author jun.wang
 * @title: UserService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/4 15:00
 */
public interface UserService {

    Page<User> findAllUser(int pageNo, int pageSize);
    User findByUsername(String username);
}
