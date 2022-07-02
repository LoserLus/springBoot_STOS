package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.service.DgzUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/user")
public class DgzUserController {

    @Autowired
    private DgzUserService dgzUserService;

    @GetMapping("/login")
    @CrossOrigin
    public R<String> Login(HttpServletRequest request, String username, String pwd){
        log.info("username: {}",username);
        log.info("pwd: {}",pwd);

        //1.根据用户名查询该用户名是否在数据库中
        LambdaQueryWrapper<DgzUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DgzUser::getDgzUsername,username);
        DgzUser dgzUser = dgzUserService.getOne(queryWrapper);

        //3.如不在，返回错误结果
        if(dgzUser ==null){
            return R.error("登录失败");
        }

        //4。如在，比较该密码是否匹配，如不匹配，返回错误结果
        if(!dgzUser.getDgzPassword().equals(pwd)){
            return R.error("登录失败");
        }

        return R.success("登录成功");
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody DgzUser dgzUser){
        log.info("dgzUser : {}",dgzUser);

        //先根据账号在数据库中查找

        //如果已经存在该账号，返回注册错误

        //如果不存在该账号，则把这个注册的用户增加到dgzuser表中

        return null;
    }

}
