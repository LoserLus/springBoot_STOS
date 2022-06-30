package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.entity.Nmuser;
import com.sdust.stos.service.NmuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class LoginController1 {

    @Autowired
    private NmuserService nmuserService;

    @RequestMapping(value = "/userlogin",method = RequestMethod.GET)
    @CrossOrigin
    public R<String> Login(HttpServletRequest request, String username, String pwd){
        log.info("username: {}",username);
        log.info("pwd: {}",pwd);


        //1.根据用户名查询该用户名是否在数据库中
        LambdaQueryWrapper<Nmuser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Nmuser::getNmUser,username);
        Nmuser nmuser = nmuserService.getOne(queryWrapper);

        //3.如不在，返回错误结果
        if(nmuser==null){
            return R.error("登录失败");
        }

        //4。如在，比较该密码是否匹配，如不匹配，返回错误结果
        if(!nmuser.getNmPassword().equals(pwd)){
            return R.error("登录失败");
        }

        return R.success("登录成功");
    }


}
