package com.sdust.stos.controller;

import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.dto.QsListDto;
import com.sdust.stos.entity.*;
import com.sdust.stos.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @ApiOperation(value = "获取订购单",notes = "不需要传参数，返回的是订购单列表")
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
    @ApiOperation(value = "发放书籍",notes = "需要发送的是一个list，其中包括订单号，订购者账号，书号，书的数量，库存量，返回的是提示信息（成功/失败）")
    public R<String> release(HttpServletRequest request, @RequestBody DgListDto dgListDto){
        log.info("发放书籍..");

        return fxMessagerService.release(request,dgListDto);
    }

    /**
     * 发送缺书单
     * @return
     */
    @PostMapping("/sendLockB")
    @ApiOperation(value = "发送缺书单",notes = "需要发送的是一个list，其中包括书号，缺书总数，订单号，返回的是提示信息（成功/失败）")
    public R<String> sendLockB(HttpServletRequest request,@RequestBody List<QsListDto> list){
        log.info("发送缺书单..");

        return fxMessagerService.sendLockB(request,list);
    }

    /**
     * 获取缺书单
     * @return
     */
    @GetMapping("/getLockB")
    @ApiOperation(value = "获取缺书单",notes = "不要传递参数，返回的是缺书列表，包括书的名称")
    public R<List<QsListDto>> getLockB(){
        log.info("获取缺书单..");

        return fxMessagerService.getLockB();
    }


    /**
     * 发行人采购功能
     * @return
     */
    @PostMapping("/purchase")
    @ApiOperation(value = "发行人采购功能",notes = "需要发送的是一个list，其中包括缺书单号，书号，缺书数量，返回的是提示信息（成功/失败）")
    public R<String> purchase(@RequestBody List<QsList> list){

        log.info("给采购人员发送进书单..");

        return fxMessagerService.purchase(list);
    }

    @GetMapping("/testOk")
    @ApiOperation(value = "订购者查看到书功能",notes = "不需要传参，返回的是到书列表")
    public R<List<JsList>> textOk(){

        return fxMessagerService.textOk();
    }

}
