package com.wj.repository;

import com.wj.entity.po.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jun.wang
 * @title: RoleMenuRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 15:38
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenu,Long> {

    List<RoleMenu> findByRoleId(Long roleId);
    int deleteByRoleId(Long roleId);
    List<RoleMenu> findByRoleIdAndParentMenuId(Long roleId, String parentMenuId);
}
