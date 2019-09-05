package com.wj.service.impl;

import com.wj.entity.po.MenuInfo;
import com.wj.entity.vo.MenuInfoVo;
import com.wj.repository.MenuInfoRepository;
import com.wj.service.MenuService;
import com.wj.service.RoleMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jun.wang
 * @title: MenuServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 17:32
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuInfoRepository menuInfoRepository;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<MenuInfoVo> findAllMenu() {
        List<MenuInfo> menuInfos = menuInfoRepository.findAllByParentIdAndDeleteFlag("0", 0);
        if (menuInfos == null || menuInfos.size() == 0) {
            return Collections.emptyList();
        }
        List<MenuInfoVo> menuInfoVoList = menuInfos.stream().map(menuInfo -> {
            MenuInfoVo menuInfoVo = new MenuInfoVo();
            BeanUtils.copyProperties(menuInfo, menuInfoVo);
            return menuInfoVo;
        }).collect(Collectors.toList());
        roleMenuService.findChildMenuInfos(menuInfoVoList);
        return menuInfoVoList;
    }
}
