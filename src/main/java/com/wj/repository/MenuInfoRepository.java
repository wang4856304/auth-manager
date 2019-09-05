package com.wj.repository;

import com.wj.entity.po.MenuInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author jun.wang
 * @title: MenuInfoRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 15:33
 */
public interface MenuInfoRepository extends JpaRepository<MenuInfo,String> {

    @Query("select m from MenuInfo m where m.parentId=:menuId and m.deleteFlag=0 order by m.sort")
    List<MenuInfo> findChildMenuInfos(@Param("menuId") String menuId);

    @Query("select m from MenuInfo m where m.id=:menuId and m.deleteFlag=0")
    MenuInfo findMenuByMenuId(@Param("menuId") String menuId);

    List<MenuInfo> findByIdInAndAndDeleteFlag(List<String> Ids, int deleteFlag);

    List<MenuInfo> findAllByParentIdAndDeleteFlag(String parent, int deleteFlag);
}
