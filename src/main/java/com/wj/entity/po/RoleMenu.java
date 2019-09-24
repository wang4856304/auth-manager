package com.wj.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;

/**
 * @author jun.wang
 * @title: RoleMenu
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 15:28
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "role_menu")
@DynamicInsert
public class RoleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "parent_menu_id")
    private String parentMenuId;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;
}
