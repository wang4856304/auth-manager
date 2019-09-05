package com.wj.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * @author jun.wang
 * @title: User
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/4 14:53
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
@DynamicInsert
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private int status;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;
}
