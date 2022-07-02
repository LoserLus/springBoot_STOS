package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.service.DgzUserService;
import com.sdust.stos.service.TextMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class DgzUserController {

    @Autowired
    private DgzUserService dgzUserService;

    @Autowired
    private TextMessageService textMessageService;

    /**
     * 登录,三种登录身份
     * @param request
     * @param username
     * @param pwd
     * @param type
     */
    @GetMapping("/login")
    @CrossOrigin
    public R<String> Login(HttpServletRequest request,String username,String pwd,String type){
        log.info("username: {}",username);
        log.info("pwd: {}",pwd);
        log.info("type: {}",type);

        //request.getSession().getAttribute();

        R<String> loginUser = dgzUserService.login(username, pwd, type);
        String data = loginUser.getData();
        if(data.equals("登录成功")){
            return R.success("登录成功");
        }
        return R.error("登录失败");

    }


    /**
     * 学生注册
     * @param dgzUser
     * @return
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody DgzUser dgzUser){
        log.info("dgzUser : {}",dgzUser);

        //先根据账号在数据库中查找
        LambdaQueryWrapper<DgzUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DgzUser::getDgzUsername,dgzUser.getDgzUsername());
        int count = dgzUserService.count(queryWrapper);

        //如果已经存在该账号，返回注册错误
        if(count>0){
            return R.error("已经存在这个账号");
        }else{
            //如果不存在该账号，则把这个注册的用户增加到dgzuser表中
            dgzUserService.save(dgzUser);
        }

        return R.success("注册成功");
    }

    /**
     * 获取仓库里面的书籍列表
     * @return
     */
    @GetMapping("/textlist")
    public R<List<TextMessage>> textlist(){

        log.info("获取仓库里面的书籍列表..");
        LambdaQueryWrapper<TextMessage> queryWrapper = new LambdaQueryWrapper<>();
        List<TextMessage> list = textMessageService.list(queryWrapper);

        return R.success(list);
    }


}
