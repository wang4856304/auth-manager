package com.wj.entity.vo;

import com.wj.entity.po.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jun.wang
 * @title: RoleVo
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/6 10:12
 */

@Data
@NoArgsConstructor
public class RoleVo extends Role {

    private List<RoleVo> childs = new ArrayList<>();
}
