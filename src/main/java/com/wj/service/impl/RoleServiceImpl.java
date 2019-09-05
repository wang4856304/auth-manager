package com.wj.service.impl;

import com.wj.entity.dto.RoleDto;
import com.wj.entity.po.Role;
import com.wj.repository.RoleRepository;
import com.wj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

/**
 * @author jun.wang
 * @title: RoleServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:35
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRole() {
        List<Role> roleList = roleRepository.findAllRole();
        if (roleList == null) {
            return Collections.emptyList();
        }
        return roleList;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findByIdAndIsEnable(id, 1);
    }

    @Override
    public List<Role> findRoleByName(String roleName) {
        List<Role> roleList = roleRepository.findRoleByName(roleName);
        if (roleList == null) {
            return Collections.emptyList();
        }
        return roleList;
    }

    @Override
    public List<Role> findChildsById(Long id) {
        List<Role> roleList = roleRepository.findChildsById(id);
        if (roleList == null) {
            return Collections.emptyList();
        }
        return roleList;
    }

    @Override
    public int addRole(RoleDto roleDto) {
        Role role = new Role();
        role.setParentId(roleDto.getParentId());
        role.setRoleName(roleDto.getRoleName());
        role.setDesc(roleDto.getDesc());
        roleRepository.save(role);
        return 1;
    }

    @Override
    public int deleteRole(Long id) {
        return roleRepository.deleteRoleById(id);
    }

    @Override
    public int updateRoleById(Long id, String roleName, String desc) {
        return roleRepository.updateRoleById(id, roleName, desc);
    }
}
