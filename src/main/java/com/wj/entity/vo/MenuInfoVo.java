package com.wj.entity.vo;

import com.wj.entity.po.MenuInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jun.wang
 * @title: MenuInfoVo
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 16:25
 */

@Data
@NoArgsConstructor
public class MenuInfoVo extends MenuInfo {

    private List<MenuInfoVo> childs;
}
