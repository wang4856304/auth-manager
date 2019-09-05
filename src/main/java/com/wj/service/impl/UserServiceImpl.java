package com.wj.service.impl;

import com.wj.entity.po.User;
import com.wj.repository.UserRepository;
import com.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author jun.wang
 * @title: UserServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/4 15:02
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public com.wj.utils.Page<User> findAllUser(int pageNo, int pageSize) {
        Page<User> page = userRepository.findAllUserByStatus(1, createPageable(pageNo, pageSize));
        com.wj.utils.Page<User> resultPage = new com.wj.utils.Page<>();
        resultPage.setData(page.getContent());
        resultPage.setTotal(page.getTotalElements());
        resultPage.setPageNo(pageNo);
        resultPage.setPageSize(pageSize);
        return resultPage;
    }

    private Pageable createPageable(int pageNo, int pageSize) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        return PageRequest.of(pageNo-1, pageSize);
    }
}
