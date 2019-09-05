package com.wj.repository;

import com.wj.entity.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jun.wang
 * @title: UserRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/4 14:59
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndStatus(String username, int status);
    Page<User> findAllUserByStatus(int status, Pageable pageable);
}
