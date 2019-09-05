package com.wj.service;

import com.wj.entity.dto.RoleDto;
import com.wj.entity.po.Role;

import java.util.List;

/**
 * @author jun.wang
 * @title: RoleService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:34
 */
public interface RoleService {

    List<Role> findAllRole();

    Role findById(Long id);

    List<Role> findRoleByName(String roleName);

    List<Role> findChildsById(Long id);

    int addRole(RoleDto roleDto);

    int deleteRole(Long id);

    int updateRoleById(Long id, String roleName, String desc);
}
