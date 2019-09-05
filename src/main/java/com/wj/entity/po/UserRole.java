package com.wj.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * @author jun.wang
 * @title: UserRole
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:11
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_role")
@DynamicInsert
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @Column(name = "role_id")
    private Long roleId;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Role role;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;
}
