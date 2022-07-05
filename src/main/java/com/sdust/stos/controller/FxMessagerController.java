package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.dto.QsListDto;
import com.sdust.stos.entity.*;
import com.sdust.stos.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/messager")
@Api(tags = {"FxMessagerController"},description = "发行人Controller")
public class FxMessagerController {

    @Autowired
    private FxMessagerService fxMessagerService;

    /**
     * 获取用户订购书籍列表
     * @return
     */
    @GetMapping("/dglist")
    @ApiOperation(value = "获取订购单")
    public R<List<DgListDto>> dglist() {

        log.info("获取用户订购书籍列表..");

        return fxMessagerService.dglist();
    }

    /**
     * 发书功能
     * @return
     */
    @PostMapping("/release")
    @Transactional
    @ApiOperation(value = "发放书籍")
    public R<String> release(HttpServletRequest request, @RequestBody DgListDto dgListDto){
        log.info("发放书籍..");

        return fxMessagerService.release(request,dgListDto);
    }

    /**
     * 发送缺书单
     * @return
     */
    @PostMapping("/sendLockB")
    @ApiOperation(value = "发送缺书单")
    public R<String> sendLockB(HttpServletRequest request,@RequestBody List<QsList> list){
        log.info("发放缺书单..");

        return fxMessagerService.sendLockB(request,list);
    }

    /**
     * 获取缺书单
     * @return
     */
    @GetMapping("/getLockB")
    @ApiOperation(value = "获取缺书单")
    public R<List<QsListDto>> getLockB(){
        log.info("获取缺书单..");

        return fxMessagerService.getLockB();
    }


    /**
     * 发行人采购功能
     * @return
     */
    @PostMapping("/purchase")
    @ApiOperation(value = "执行采购功能")
    public R<String> purchase(List<DgListDto> list){

        //把缺的书汇总起来保存到进书表中

        return fxMessagerService.purchase(list);
    }



}
