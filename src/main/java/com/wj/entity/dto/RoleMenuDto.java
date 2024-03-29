package com.wj.entity.dto;

import com.wj.entity.vo.MenuInfoVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jun.wang
 * @title: RoleMenuDto
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 17:44
 */

@Data
@NoArgsConstructor
public class RoleMenuDto {

    private Long roleId;

    private List<String> menuIds;

    private List<MenuInfoVo> menuInfoVoList = new ArrayList<>();
}
