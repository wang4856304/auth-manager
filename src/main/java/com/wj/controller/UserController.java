package com.wj.controller;

import com.wj.entity.po.User;
import com.wj.service.UserService;
import com.wj.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jun.wang
 * @title: UserController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/4 15:09
 */

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAllUser")
    public ResponseEntity<Page<User>> findAllUser(@RequestParam int pageNo, @RequestParam int pageSize) {
        return buildSuccessResponse(userService.findAllUser(pageNo, pageSize));
    }
}
