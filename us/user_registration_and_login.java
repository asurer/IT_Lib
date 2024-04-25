
package com.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.service.UsersService;
import com.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.UsersEntity;
import com.service.TokenService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 登录相关
 */
@RequestMapping("users")
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        UsersEntity user = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
        if (user == null || !user.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        String token = tokenService.generateToken(user.getId(), username, "users", user.getRole());
        R r = R.ok();
        r.put("token", token);
        r.put("role", user.getRole());
        r.put("userId", user.getId());
        return r;
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody UsersEntity user) {
        ValidatorUtils.validateEntity(user);
        if (usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername())) != null) {
            return R.error("用户已存在");
        }
        usersService.insert(user);
        return R.ok();
    }

    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }

}
