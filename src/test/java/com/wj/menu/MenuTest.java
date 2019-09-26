package com.wj.menu;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.vo.MenuInfoVo;
import com.wj.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jun.wang
 * @title: MenuTest
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/18 11:15
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {

    @Autowired
    private MenuService menuService;


    @Test
    public void findParentMenuInfos() {
        MenuInfoVo menuInfoVo = new MenuInfoVo();
        menuInfoVo.setId("4");
    }
}
