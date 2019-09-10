package com.wj.service.impl;

import com.wj.entity.dto.RoleDto;
import com.wj.entity.po.Role;
import com.wj.entity.vo.RoleVo;
import com.wj.repository.RoleRepository;
import com.wj.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 获取角色树
     * @return
     */
    @Override
    public RoleVo findAllRoleById(Long id) {
        Role role = roleRepository.findByIdAndIsEnable(id, 1);
        RoleVo roleVo = new RoleVo();
        if (role != null) {
            BeanUtils.copyProperties(role, roleVo);
            findAllChildById(roleVo);
        }
        return roleVo;
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
        if (roleList == null || roleList.size() == 0) {
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

    @Override
    public List<Long> findAllRoleIdsById(Long id) {
        Set<Long> roleIds = new HashSet<>();
        Role role = roleRepository.findByIdAndIsEnable(id, 1);
        if (role != null) {
            roleIds.add(role.getId());
        }
        findAllChildById(id, roleIds);
        return new ArrayList<>(roleIds);
    }

    private void findAllChildById(Long id, Set<Long> roleIds) {
        List<Role> roleList = findChildsById(id);
        if (roleList != null && roleList.size() > 0) {
            roleList.stream().map(role -> roleIds.add(role.getId())).collect(Collectors.toList());
            roleList.stream().map(childRole->{
                findAllChildById(childRole.getId(), roleIds);
                return 0;
            }).collect(Collectors.toList());
        }
    }

    private void findAllChildById(RoleVo roleVo) {
        List<Role> roleList = findChildsById(roleVo.getId());
        if (roleList != null && roleList.size() > 0) {
            List<RoleVo> roleVoList = roleList.stream().map(role -> {
                RoleVo childRoleVo = new RoleVo();
                BeanUtils.copyProperties(role, childRoleVo);
                return childRoleVo;
            }).collect(Collectors.toList());
            roleVo.setChilds(roleVoList);
            roleVoList.stream().map(childRoleVo->{
                findAllChildById(childRoleVo);
                return 0;
            }).count();
        }
    }
}
