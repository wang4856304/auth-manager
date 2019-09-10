package com.wj.controller;


import com.wj.entity.vo.RoleVo;
import com.wj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author jun.wang
 * @title: RoleController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/9 15:09
 */

@RequestMapping("/api/role")
@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/findAllRoleById")
    public ResponseEntity<RoleVo> findAllRoleById(@RequestParam Long id) {
        return buildSuccessResponse(roleService.findAllRoleById(id));
    }
}
