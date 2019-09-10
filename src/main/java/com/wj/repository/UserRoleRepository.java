package com.wj.repository;

import com.wj.entity.po.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author jun.wang
 * @title: UserRoleRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:31
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Query(value = "select id, user_id, role_id, create_at, update_at from user_role where user_id=:userId for update", nativeQuery = true)
    UserRole findByUserId(@Param("userId") Long userId);

    int deleteByUserId(Long userId);
}
