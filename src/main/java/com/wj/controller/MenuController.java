package com.wj.controller;

import com.wj.entity.vo.MenuInfoVo;
import com.wj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jun.wang
 * @title: MenuController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/24 11:19
 */

@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/findAllMenu")
    public ResponseEntity<List<MenuInfoVo>> findAllMenu() {
        return buildSuccessResponse(menuService.findAllMenu());
    }
}
