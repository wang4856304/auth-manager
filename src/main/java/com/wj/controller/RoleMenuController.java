package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.dto.RoleMenuDto;
import com.wj.entity.vo.MenuInfoVo;
import com.wj.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jun.wang
 * @title: RoleMenuController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 15:43
 */

@RestController
@RequestMapping("/api/roleMenu")
public class RoleMenuController extends BaseController {

    @Autowired
    private RoleMenuService roleMenuService;


    @RequestMapping("/findMenuByRole")
    public ResponseEntity<List<MenuInfoVo>> findMenuByRole(@RequestParam long roleId) {
        return buildSuccessResponse(roleMenuService.findByRoleId(roleId));
    }

    @RequestMapping("/addRoleMenu")
    public ResponseEntity<JSONObject> addRoleMenu(@RequestBody RoleMenuDto roleMenuDto) {
        int size = roleMenuService.addRoleMenu(roleMenuDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("no", size);
        return buildSuccessResponse(jsonObject);
    }
}
