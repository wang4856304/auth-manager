package com.wj.repository;

import com.wj.entity.po.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jun.wang
 * @title: UserRoleRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:31
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    UserRole findByUserId(Long userId);
}
