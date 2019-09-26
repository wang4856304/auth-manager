package com.wj.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
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
import java.util.ArrayList;
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

    private final static String ROOT_MENU_ID = "0";//顶层节点

    @Override
    public List<MenuInfoVo> findByRoleId(Long roleId) {
        List<RoleMenu> roleMenus = roleMenuRepository.findByRoleIdAndParentMenuId(roleId, ROOT_MENU_ID);
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
        findMenuTreeByRoleId(roleId, menuInfoVoList);
        return menuInfoVoList;
    }

    private void findMenuTreeByRoleId(Long roleId, List<MenuInfoVo> menuInfoVoList) {
        menuInfoVoList.stream().map(menuInfoVo -> {
            List<RoleMenu> roleMenus = roleMenuRepository.findByRoleIdAndParentMenuId(roleId, menuInfoVo.getId());
            if (roleMenus != null && roleMenus.size() != 0) {
                List<String> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
                List<MenuInfo> menuInfos = menuInfoRepository.findByIdInAndAndDeleteFlag(menuIds, 0);
                List<MenuInfoVo> childMenuInfoVoList = menuInfos.stream().map(menuInfo -> {
                    MenuInfoVo childMenuInfoVo = new MenuInfoVo();
                    BeanUtils.copyProperties(menuInfo, childMenuInfoVo);
                    return childMenuInfoVo;
                }).collect(Collectors.toList());
                menuInfoVo.setChilds(childMenuInfoVoList);
                findMenuTreeByRoleId(roleId, childMenuInfoVoList);
            }
            return 0;
        }).count();

    }

    @Override
    @Transactional
    //@TxcTransaction
    @LcnTransaction
    public int addRoleMenu(RoleMenuDto roleMenuDto) {
        Long roleId = roleMenuDto.getRoleId();
        roleMenuRepository.deleteByRoleId(roleId);
        List<RoleMenu> roleMenuList = new ArrayList<>();
        phraseRoleMenu(roleMenuList, roleMenuDto.getMenuInfoVoList(), roleId);
        return roleMenuRepository.saveAll(roleMenuList).size();
    }

    /**
     * 解析获取角色对应的权限
     * @param roleMenuList
     * @param menuInfoVoList
     * @param roleId
     */
    private void phraseRoleMenu(List<RoleMenu> roleMenuList, List<MenuInfoVo> menuInfoVoList, Long roleId) {
        if (menuInfoVoList == null || menuInfoVoList.size() == 0) {
            return;
        }
        menuInfoVoList.stream().map(menuInfoVo -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuInfoVo.getId());
            roleMenu.setParentMenuId(menuInfoVo.getParentId());
            roleMenuList.add(roleMenu);
            phraseRoleMenu(roleMenuList, menuInfoVo.getChilds(), roleId);
            return 0;
        }).count();
    }
}
