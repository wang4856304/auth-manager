package com.wj.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * @author jun.wang
 * @title: MenuInfo
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 14:59
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "menu_info")
@DynamicInsert
public class MenuInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "type")
    private int type;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "url")
    private String url;

    @Column(name = "sort")
    private int sort;

    @Column(name = "delete_flag")
    private int deleteFlag = 0;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;
}
