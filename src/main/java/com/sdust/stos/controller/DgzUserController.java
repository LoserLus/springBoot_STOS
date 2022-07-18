package com.sdust.stos.controller;


import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.LsList;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.service.DgzUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = {"DgzUserController"},description = "订购者Controller")
public class DgzUserController {

    @Autowired
    private DgzUserService dgzUserService;

    /**
     * 学生注册
     * @param dgzUser
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "学生注册功能",notes = "需要发送的是一个list，其中包括缺书单号，书号，缺书数量；返回的是注册成功或者失败")
    public R<String> register(@RequestBody DgzUser dgzUser) {
        log.info("dgzUser : {}", dgzUser);

        return dgzUserService.register(dgzUser);
    }

    /**
     * 获取仓库里面的书籍列表
     *
     * @return
     */
    @GetMapping("/textlist")
    @ApiOperation(value = "订购者获取书库列表",notes = "不需要传参，返回的是书库列表")
    public R<List<TextMessage>> textlist() {
        log.info("获取仓库里面的书籍列表..");

        return dgzUserService.textlist();
    }

    /**
     * 用户确认订购,存入订购表
     *
     * @param list
     * @return
     */
    @PostMapping("/textorder")
    @ApiOperation(value = "订购者订购功能",notes = "需要的是一个list，其中包括书号，数量，返回的是订购成功")
    public R<String> textorder(HttpServletRequest request, @RequestBody List<DgListDto> list) {
        for (int i = 0; i < list.size(); i++) {
            DgListDto dgListDto = list.get(i);
            log.info("订购的书籍为：{}", dgListDto);
        }

       return dgzUserService.textorder(request,list);
    }


    @GetMapping("/getText")
    @ApiOperation(value = "订购者获取领书表",notes = "不需要传参，返回的是领书列表")
    public R<List<LsList>> getText(HttpServletRequest request){

        return dgzUserService.getText(request);
    }


}
