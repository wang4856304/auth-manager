package com.wj.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * @author jun.wang
 * @title: Role
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:07
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
@DynamicInsert
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "desc")
    private String desc;

    @Column(name = "is_enable")
    private Integer isEnable = 1;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;
}
