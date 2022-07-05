package com.sdust.stos.controller;

import com.sdust.stos.common.R;
import com.sdust.stos.entity.JsList;
import com.sdust.stos.service.CgMessagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cgMessager")
@Api(tags = {"CgMessagerController"},description = "采购者Controller")
public class CgMessagerController {

    @Autowired
    private CgMessagerService cgMessagerService;

    @GetMapping("/getJsList")
    @ApiOperation(value = "采购人获取进书单列表",notes = "不需要传参，返回的是书库列表")
    public R<List<JsList>> getJsList(){
        log.info("获取仓库里面的进书列表..");

        return cgMessagerService.getJsList();
    }

    @PostMapping("/purchase")
    @ApiOperation(value = "采购人执行采购功能",notes = "需要发送的是一个list，其中包括进书单号，书号，数量；返回的是注册成功或者失败")
    public R<String> purchase(HttpServletRequest request,@RequestBody List<JsList> list){
        log.info("采购加库存..");

        return cgMessagerService.purchase(request,list);
    }

}
