package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.DgList;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.service.DgListService;
import com.sdust.stos.service.DgzUserService;
import com.sdust.stos.service.TextMessageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = {"DgzUserController"},description = "订购者Controller")
public class DgzUserController {

    @Autowired
    private DgzUserService dgzUserService;

    @Autowired
    private TextMessageService textMessageService;

    @Autowired
    private DgListService dgListService;

    /**
     * 学生注册
     *
     * @param dgzUser
     * @return
     */
    @PostMapping("/register")
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
    public R<String> textorder(HttpServletRequest request, @RequestBody List<DgListDto> list) {
        for (int i = 0; i < list.size(); i++) {
            DgListDto dgListDto = list.get(i);
            log.info("订购的书籍为：{}", dgListDto);
        }

       return dgzUserService.textorder(request,list);
    }

}
