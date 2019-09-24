package com.wj.service.impl;

import com.wj.entity.po.MenuInfo;
import com.wj.entity.vo.MenuInfoVo;
import com.wj.repository.MenuInfoRepository;
import com.wj.service.MenuService;
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
        findChildMenuInfos(menuInfoVoList);
        return menuInfoVoList;
    }

    @Override
    public MenuInfoVo findParentMenuInfos(MenuInfoVo menuInfoVo) {
        MenuInfo menuInfo = menuInfoRepository.findMenuByMenuId(menuInfoVo.getId());
        MenuInfoVo parentMenuInfoVo = new MenuInfoVo();
        if (menuInfo != null) {
            MenuInfo parentMenuInfo = menuInfoRepository.findMenuByMenuId(menuInfo.getParentId());
            if (parentMenuInfo != null) {
                BeanUtils.copyProperties(parentMenuInfo, parentMenuInfoVo);
                parentMenuInfoVo.getChilds().add(menuInfoVo);
                if (!"0".equals(parentMenuInfo.getParentId())) {
                    parentMenuInfoVo = findParentMenuInfos(parentMenuInfoVo);
                }
            }
        }
        return parentMenuInfoVo;
    }

    private void findChildMenuInfos(List<MenuInfoVo> menuInfoVoList) {
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
}
