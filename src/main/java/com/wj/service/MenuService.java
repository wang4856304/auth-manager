package com.wj.service;

import com.wj.entity.vo.MenuInfoVo;

import java.util.List;

/**
 * @author jun.wang
 * @title: MenuService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 17:31
 */
public interface MenuService {

    List<MenuInfoVo> findAllMenu();
    MenuInfoVo findParentMenuInfos(MenuInfoVo menuInfoVo);
}
