package com.wj.service;

import com.wj.entity.dto.RoleMenuDto;
import com.wj.entity.vo.MenuInfoVo;
import java.util.List;

/**
 * @author jun.wang
 * @title: RoleMenuService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 15:40
 */
public interface RoleMenuService {

    List<MenuInfoVo> findByRoleId(Long roleId);
    void findChildMenuInfos(List<MenuInfoVo> menuInfoVoList);
    int addRoleMenu( RoleMenuDto roleMenuDto);
}
