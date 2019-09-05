package com.wj.service.impl;

import com.wj.entity.dto.RoleMenuDto;
import com.wj.entity.po.MenuInfo;
import com.wj.entity.po.RoleMenu;
import com.wj.entity.vo.MenuInfoVo;
import com.wj.repository.MenuInfoRepository;
import com.wj.repository.RoleMenuRepository;
import com.wj.service.RoleMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jun.wang
 * @title: RoleMenuServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 15:41
 */

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Autowired
    private MenuInfoRepository menuInfoRepository;

    @Override
    public List<MenuInfoVo> findByRoleId(Long roleId) {
        List<RoleMenu> roleMenus = roleMenuRepository.findByRoleId(roleId);
        if (roleMenus == null || roleMenus.size() == 0) {
            return Collections.emptyList();
        }
        List<String> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<MenuInfo> menuInfos = menuInfoRepository.findByIdInAndAndDeleteFlag(menuIds, 0);
        if (menuInfos == null || menuInfos.size() == 0) {
            return Collections.emptyList();
        }
        List<MenuInfoVo> menuInfoVoList = menuInfos.stream().map(menuInfo -> {
            MenuInfoVo menuInfoVo = new MenuInfoVo();
            BeanUtils.copyProperties(menuInfo, menuInfoVo);
            return menuInfoVo;
        }).collect(Collectors.toList());
        findChildMenuInfos(menuInfoVoList);
        return menuInfoVoList;
    }

    public void findChildMenuInfos(List<MenuInfoVo> menuInfoVoList) {
        menuInfoVoList.stream().map(menuInfoVo -> {
            List<MenuInfo> childMenuInfos = menuInfoRepository.findChildMenuInfos(menuInfoVo.getId());
            if (childMenuInfos != null && childMenuInfos.size() != 0) {
                List<MenuInfoVo> childMenuInfoVoList = childMenuInfos.stream().map(menuInfo -> {
                    MenuInfoVo menuInfoVoX = new MenuInfoVo();
                    BeanUtils.copyProperties(menuInfo, menuInfoVoX);
                    return menuInfoVoX;
                }).collect(Collectors.toList());
                menuInfoVo.setChilds(childMenuInfoVoList);
                findChildMenuInfos(childMenuInfoVoList);
            }
            return 0;
        }).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public int addRoleMenu(RoleMenuDto roleMenuDto) {
        Long roleId = roleMenuDto.getRoleId();
        roleMenuRepository.deleteByRoleId(roleId);
        List<String> menuIds = roleMenuDto.getMenuIds();
        if (menuIds == null || menuIds.size() == 0) {
            return 0;
        }
        List<RoleMenu> roleMenus = menuIds.stream().map(id->{
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(id);
            return roleMenu;
        }).collect(Collectors.toList());
        return roleMenuRepository.saveAll(roleMenus).size();
    }
}
