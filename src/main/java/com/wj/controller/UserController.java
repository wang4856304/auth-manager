package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.po.User;
import com.wj.entity.po.UserRole;
import com.wj.entity.vo.MenuInfoVo;
import com.wj.service.RoleMenuService;
import com.wj.service.UserRoleService;
import com.wj.service.UserService;
import com.wj.utils.HttpClientUtil;
import com.wj.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    private final static String OAUTH_URL = "http://localhost:9920/";

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping("/findAllUser")
    public ResponseEntity<Page<User>> findAllUser(@RequestParam int pageNo, @RequestParam int pageSize) {
        return buildSuccessResponse(userService.findAllUser(pageNo, pageSize));
    }

    @GetMapping("/findUserMenu")
    public ResponseEntity<List<MenuInfoVo>> findUserMenu(HttpServletRequest request) {
        String accessToken = request.getHeader("access_token");
        List<MenuInfoVo> menuInfoVoList = new ArrayList<>();
        String url = OAUTH_URL + "/uaa/user?access_token=" + accessToken;
        logger.info("request param={}", url);
        String result = HttpClientUtil.httpGetRequest(url);
        logger.info("response={}", result);
        if (StringUtils.isEmpty(result)) {
            throw new RuntimeException("get user info error");
        }
        JSONObject resultJson = JSONObject.parseObject(result);
        String code = resultJson.getString("code");
        if ("0".equals(code)) {
            String username = resultJson.getJSONObject("data").getString("username");
            User user = userService.findByUsername(username);
            if (user != null) {
                UserRole userRole = userRoleService.findByUserId(user.getId());
                if (userRole != null) {
                    menuInfoVoList =roleMenuService.findByRoleId(userRole.getRoleId());
                }
            }
        }
        return buildSuccessResponse(menuInfoVoList);
    }
}
