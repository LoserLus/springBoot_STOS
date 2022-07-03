package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.DgList;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.FxMessager;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.service.DgListService;
import com.sdust.stos.service.FxMessagerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/messager")
@Api(tags = {"FxMessagerController"},description = "发行人Controller")
public class FxMessagerController {

    @Autowired
    private FxMessagerService fxMessagerService;

    @Autowired
    private DgListService dgListService;

    /**
     * 获取用户订购书籍列表
     * @return
     */
    @GetMapping("/dglist")
    public R<List<DgListDto>> dglist() {

        log.info("获取用户订购书籍列表..");

        LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(DgList::getDgDate);

        //获取订购列表
        List<DgList> dgList = dgListService.list(queryWrapper);

        //在订购列表中加入对应书号的名称
        //

        List<DgListDto> dgListDto = null;

        for(int i=0;i<dgList.size();i++){

        }




        return null;
    }

}
