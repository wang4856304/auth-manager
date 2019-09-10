package com.wj.repository;

import com.wj.entity.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jun.wang
 * @title: RoleRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 10:25
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select r from Role r where r.isEnable=1 and r.parentId=0")
    List<Role> findAllRole();

    Role findByIdAndIsEnable(Long id, int isEnable);

    @Query("select r from Role r where roleName like :roleName% and isEnable=1")
    List<Role> findRoleByName(@Param("roleName") String roleName);

    @Query("select r from Role r where parentId=:id and isEnable=1")
    List<Role> findChildsById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update role set is_enable=0 where id=:id and is_enable=1", nativeQuery = true)
    int deleteRoleById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update role set role_name=:roleName, desc=:desc where id=:id and is_enable=1", nativeQuery = true)
    int updateRoleById(@Param("id") Long id, @Param("roleName")String roleName, @Param("desc")String desc);
}
