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

    @Autowired
    private DgListService dgListService;

    @Autowired
    private TextMessageService textMessageService;

    @Autowired
    private InTableService inTableService;

    @Autowired
    private LsListService lsListService;

    /**
     * 获取用户订购书籍列表
     * @return
     */
    @GetMapping("/dglist")
    @ApiOperation(value = "获取订购单")
    public R<List<DgListDto>> dglist() {

        log.info("获取用户订购书籍列表..");

        LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(DgList::getDgDate);

        //获取订购列表
        List<DgList> dgList = dgListService.list(queryWrapper);

        //通过stream流遍历dgList,
        List<DgListDto> dgListDto = dgList.stream().map((item)->{

            //创建一个dgListDto
            DgListDto dgListDto1 = new DgListDto();

            //copy dgList到dgListDto
            BeanUtils.copyProperties(item,dgListDto1);

            //获取这条订购记录的书号
            String isbn = item.getIsbn();

            TextMessage textMessage = textMessageService.getById(isbn);

            //设置书的名称
            if(textMessage != null){
                String bookName = textMessage.getBookName();
                dgListDto1.setBookName(bookName);
            }

            //从书库中获取这本书的库存
            LambdaQueryWrapper<InTable> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(InTable::getIsbn,isbn);
            InTable inTable = inTableService.getOne(queryWrapper1);

            if(inTable != null){
                Integer stock = inTable.getStock();
                dgListDto1.setStock(stock);
            }

            return dgListDto1;
        }).collect(Collectors.toList());

        return R.success(dgListDto);
    }

    /**
     * 发书功能
     * @return
     */
    @PostMapping("/release")
    @Transactional
    @ApiOperation(value = "发放书籍")
    public R<String> release(@RequestBody DgListDto dgListDto){

        //根据书号从书库获取这本书的库存信息
        LambdaQueryWrapper<InTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InTable::getIsbn,dgListDto.getIsbn());
        InTable inTable = inTableService.getOne(queryWrapper);

        //修改这本书的库存数量
        Integer dgTotal = dgListDto.getDgTotal();
        Integer stock = dgListDto.getStock();
        int nowStock = stock - dgTotal;

        //把这本书的库存重新更新到库存表中
        inTable.setStock(nowStock);
        inTableService.update(inTable,queryWrapper);

        //这个订书单完成，加入到领书单里面
        LsList lsList = new LsList();
        lsList.setLsId("LS"+ System.currentTimeMillis());
        lsList.setDgId(dgListDto.getDgId());
        lsList.setLsDate(LocalDateTime.now());
        lsList.setLsUsername(dgListDto.getFxUsername());
        lsListService.save(lsList);

        //把这个订单删除
        LambdaQueryWrapper<DgList> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(DgList::getDgId,dgListDto.getDgId());
        dgListService.remove(queryWrapper1);

        return R.success("发书成功");
    }

    /**
     * 发送缺书单
     * @return
     */
    @PostMapping("/sendLockB")
    @ApiOperation(value = "发送缺书单")
    public R<String> sendLockB(List<QsList> list){

        return null;
    }


    @GetMapping("/getLockB")
    @ApiOperation(value = "获取缺书单")
    public R<List<QsListDto>> getLockB(){

        return null;
    }


    /**
     * 采购功能
     * @return
     */
    @PostMapping("/purchase")
    @ApiOperation(value = "执行采购功能")
    public R<String> purchase(List<DgListDto> list){

        //把缺的书汇总起来保存到进书表中


        return null;
    }





}
