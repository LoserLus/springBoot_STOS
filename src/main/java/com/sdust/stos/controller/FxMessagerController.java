package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.FxMessager;
import com.sdust.stos.service.FxMessagerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/messager")
@Api(tags = {"FxMessagerController"},description = "发行人Controller")
public class FxMessagerController {

    @Autowired
    private FxMessagerService fxMessagerService;

    @GetMapping("/login")
    public R<String> Login(HttpServletRequest request, String username, String pwd) {
        log.info("username: {}", username);
        log.info("pwd: {}", pwd);

        //在session中设置当前登录的用户账号
        request.getSession().setAttribute("nowusername", username);

        //发行人登录
        //1.根据用户账号查询该账号是否在数据库中
        LambdaQueryWrapper<FxMessager> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FxMessager::getFxUsername, username);
        FxMessager fxMessager = fxMessagerService.getOne(queryWrapper);

        //3.如不在，返回错误结果
        if (fxMessager == null) {
            return R.error("登录失败");
        }

        //4。如在，比较该密码是否匹配，如不匹配，返回错误结果
        if (!fxMessager.getFxPassword().equals(pwd)) {
            return R.error("登录失败");
        }

        return R.success("登录成功");

    }


}
