package com.wj.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jun.wang
 * @title: RoleDto
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:38
 */

@Data
@NoArgsConstructor
public class RoleDto {

    private Long parentId;

    private String roleName;

    private String desc;

    private Integer isEnable = 1;
}
